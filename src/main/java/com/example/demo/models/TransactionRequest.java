package com.example.demo.models;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

/**
 * @author YoussefMahmoud
 * @created Apr 23, 2023-4:33:25 PM
 */

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransactionRequest {

	Double amount;
	String date;
	String transactionType;
	String receiver;
	String accountNumber;

}