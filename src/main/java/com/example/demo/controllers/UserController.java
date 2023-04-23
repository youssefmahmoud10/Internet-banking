package com.example.demo.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.models.AccountResponse;
import com.example.demo.models.ProfileResponse;
import com.example.demo.models.TransactionRequest;
import com.example.demo.models.TransactionResponse;
import com.example.demo.models.UserRequest;
import com.example.demo.models.UserResponse;
import com.example.demo.services.UserServices;

/**
 * @author YoussefMahmoud
 * @created Apr 23, 2023-4:29:44 PM
 */

@RestController
@RequestMapping("userController") //http://localhost:8080/userController/register
public class UserController {

	@Autowired
	private UserServices userServices;

	@CrossOrigin
	@PutMapping(path = "/register")
	public Integer register(@RequestBody UserRequest userRequest) {
		return userServices.register(userRequest);
	}

	@CrossOrigin
	@PostMapping(path = "/login")
	public Integer login(@RequestBody UserRequest userRequest) {
		return userServices.logIn(userRequest);
	}

	@CrossOrigin
	@PostMapping(path = "/getUserData")
	public UserResponse getUserData(@RequestBody UserRequest userRequest) {
		return userServices.getUserData(userRequest);
	}

	@CrossOrigin
	@PostMapping(path = "/getAccountData")
	public AccountResponse getAccountData(@RequestBody UserRequest userRequest) {
		return userServices.getAccountData(userRequest);
	}

	@CrossOrigin
	@PostMapping(path = "/	")
	public List<TransactionResponse> getTransactionsData(@RequestBody UserRequest userRequest) {
		return userServices.getTransactionsData(userRequest);
	}

	@CrossOrigin
	@PostMapping(path = "/getProfileData")
	public ProfileResponse getProfileData(@RequestBody UserRequest userRequest) {
		return userServices.getProfileData(userRequest);
	}

	@CrossOrigin
	@PostMapping(path = "/makeTransaction")
	public Integer makeTransaction(@RequestBody TransactionRequest transactionRequest) {
		return userServices.makeTransaction(transactionRequest);
	}

}