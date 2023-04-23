package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entities.AccountEntity;
import com.example.demo.entities.TransactionEntity;
import com.example.demo.entities.UserEntity;
import com.example.demo.models.AccountResponse;
import com.example.demo.models.ProfileResponse;
import com.example.demo.models.SaveRegisterRequest;
import com.example.demo.models.TransactionRequest;
import com.example.demo.models.TransactionResponse;
import com.example.demo.models.UserRequest;
import com.example.demo.models.UserResponse;
import com.example.demo.repositories.AccountRepository;
import com.example.demo.repositories.TransactionRepository;
import com.example.demo.repositories.UserRepository;

/**
 * @author YoussefMahmoud
 * @created Apr 23, 2023-4:42:32 PM
 */

@Service
public class UserServices {

	@Autowired
	private UserRepository usersRepository;
	@Autowired
	private TransactionRepository transactionRepository;
	@Autowired
	private AccountRepository accountRepository;

	public ProfileResponse getProfileData(UserRequest userRequest) {
		ProfileResponse profileResponse = new ProfileResponse();
		AccountEntity accountEntity = accountRepository.findById(userRequest.getAccountNumber()).get();
		UserEntity userEntity = usersRepository.findById(accountEntity.getUserNid()).get();
		profileResponse.setName(userEntity.getName());
		profileResponse.setBalance(accountEntity.getBalance());
		return profileResponse;
	}

	public boolean existsInAccounts(UserRequest userRequest) {
		return accountRepository.existsById(userRequest.getAccountNumber());
	}

	public boolean alreadyRegistered(UserRequest userRequest) {
		AccountEntity accountEntity = accountRepository.findById(userRequest.getAccountNumber()).get();
		return accountEntity.getHasIb().equals("y");
	}

	public boolean isValidPassword(UserRequest userRequest) {
		Pattern pattern = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%*])(?!.* ).{8,20}$");
		Matcher matcher = pattern.matcher(userRequest.getPassword());
		return matcher.find();
	}

	public boolean checkPassword(UserRequest userRequest) {
		AccountEntity accountEntity = accountRepository.findById(userRequest.getAccountNumber()).get();
		return accountEntity.getPassword().equals(userRequest.getPassword());
	}

	public Integer register(UserRequest userRequest) {
		if (existsInAccounts(userRequest)) {
			if (alreadyRegistered(userRequest)) {
				return 1; // User has registered already before
			} else {
				AccountEntity accountEntity = accountRepository.findById(userRequest.getAccountNumber()).get();
				SaveRegisterRequest saveRegisterRequest = new SaveRegisterRequest();
				if (isValidPassword(userRequest)) {
					saveRegisterRequest.setAccountNumber(userRequest.getAccountNumber());
					saveRegisterRequest.setHasIb("y");
					saveRegisterRequest.setPassword(userRequest.getPassword());
					saveRegisterRequest.setUserNid(accountEntity.getUserNid());
					BeanUtils.copyProperties(saveRegisterRequest, accountEntity);
					accountRepository.save(accountEntity);
					return 2; // registered successfully
				} else {
					return 3; // unvalid password
				}
			}
		} else {
			return 0; // User does not exist in bank database
		}
	}

	public Integer logIn(UserRequest userRequest) {
		if (existsInAccounts(userRequest)) {
			if (alreadyRegistered(userRequest)) {
				if (checkPassword(userRequest)) {
					return 1; // login successfully
				} else {
					return 3; // wrong password
				}
			} else {
				return 2; // User need to register first
			}
		} else {
			return 0; // User does not exist in bank database
		}
	}

	public UserResponse getUserData(UserRequest userRequest) {
		UserResponse userResponse = new UserResponse();
		AccountEntity accountEntity = accountRepository.findById(userRequest.getAccountNumber()).get();
		UserEntity userEntity = usersRepository.findById(accountEntity.getUserNid()).get();
		BeanUtils.copyProperties(userEntity, userResponse);
		return userResponse;
	}

	public AccountResponse getAccountData(UserRequest userRequest) {
		AccountResponse accountResponse = new AccountResponse();
		AccountEntity accountEntity = accountRepository.findById(userRequest.getAccountNumber()).get();
		BeanUtils.copyProperties(accountEntity, accountResponse);
		return accountResponse;
	}

	public List<TransactionResponse> getTransactionsData(UserRequest userRequest) {
		List<TransactionResponse> allTransactions = new ArrayList<>();
		List<TransactionEntity> transactionEntities = transactionRepository.findAll();
		for (TransactionEntity transactionEntity : transactionEntities) {
			TransactionResponse transactionResponse = new TransactionResponse();
			BeanUtils.copyProperties(transactionEntity, transactionResponse);
			if (transactionEntity.getAccountNumber().equals(userRequest.getAccountNumber())) {
				allTransactions.add(transactionResponse);
			}
		}
		return allTransactions;
	}

	public Integer checkBalance(Double balance, Double amount) {
		if (Double.compare(balance, amount) == 0 || Double.compare(balance, amount) > 0) {
			return 1;
		} else {
			return 0;
		}
	}

	public Integer makeTransaction(TransactionRequest transactionRequest) {
		AccountEntity accountEntitySender = accountRepository.findById(transactionRequest.getAccountNumber()).get();
		if (checkBalance(accountEntitySender.getBalance(), transactionRequest.getAmount()) == 1) {
			if (transactionRequest.getTransactionType().equals("payment")) {
				TransactionEntity transactionEntitySender = new TransactionEntity();
				transactionEntitySender.setAmount(transactionRequest.getAmount());
				transactionEntitySender.setDate(transactionRequest.getDate());
				transactionEntitySender.setTransactionType(transactionRequest.getTransactionType());
				transactionEntitySender.setReceiver(transactionRequest.getReceiver());
				transactionEntitySender.setAccountNumber(transactionRequest.getAccountNumber());
				transactionRepository.save(transactionEntitySender);
				accountEntitySender.setBalance(accountEntitySender.getBalance() - transactionRequest.getAmount());
				accountRepository.save(accountEntitySender);
				return 1; // successful payment
			} else if (transactionRequest.getTransactionType().equals("transfer_to")) {
				if (accountRepository.existsById(transactionRequest.getReceiver())) {
					AccountEntity accountEntityReceiver = accountRepository.findById(transactionRequest.getReceiver())
							.get();
					TransactionEntity transactionEntitySender = new TransactionEntity();
					transactionEntitySender.setAmount(transactionRequest.getAmount());
					transactionEntitySender.setDate(transactionRequest.getDate());
					transactionEntitySender.setTransactionType(transactionRequest.getTransactionType());
					transactionEntitySender.setReceiver(transactionRequest.getReceiver());
					transactionEntitySender.setAccountNumber(transactionRequest.getAccountNumber());
					transactionRepository.save(transactionEntitySender);
					accountEntitySender.setBalance(accountEntitySender.getBalance() - transactionRequest.getAmount());
					accountRepository.save(accountEntitySender);

					TransactionEntity transactionEntityReceiver = new TransactionEntity();
					transactionEntityReceiver.setDate(transactionRequest.getDate());
					transactionEntityReceiver.setTransactionType("transfer_from");
					transactionEntityReceiver.setReceiver(accountEntitySender.getAccountNumber());
					transactionEntityReceiver.setAccountNumber(transactionRequest.getReceiver());

					if (accountEntitySender.getCurrency().equals(accountEntityReceiver.getCurrency())) {
						transactionEntityReceiver.setAmount(transactionRequest.getAmount());
						transactionRepository.save(transactionEntityReceiver);
						accountEntityReceiver
								.setBalance(accountEntityReceiver.getBalance() + transactionRequest.getAmount());
					} else if (accountEntitySender.getCurrency().equals("local")
							&& accountEntityReceiver.getCurrency().equals("foreign")) {
						transactionEntityReceiver.setAmount(transactionRequest.getAmount() / 25);
						transactionRepository.save(transactionEntityReceiver);
						accountEntityReceiver
								.setBalance(accountEntityReceiver.getBalance() + transactionRequest.getAmount() / 25);
					} else {
						transactionEntityReceiver.setAmount(transactionRequest.getAmount() * 25);
						transactionRepository.save(transactionEntityReceiver);
						accountEntityReceiver
								.setBalance(accountEntityReceiver.getBalance() + transactionRequest.getAmount() * 25);
					}
					accountRepository.save(accountEntityReceiver);
					return 2; // successful transfer
				} else {
					return 4; // receiver account not in the bank
				}

			} else {
				return 3; // third option
			}
		} else {
			return 0; // balance less than amount
		}
	}

}