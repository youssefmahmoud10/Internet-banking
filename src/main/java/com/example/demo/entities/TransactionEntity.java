package com.example.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

/**
 * @author YoussefMahmoud
 * @created Apr 23, 2023-4:25:21 PM
 */

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "TRANSACTION")
public class TransactionEntity {

	@Column(name = "TRANSACTION_ID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer transactionId;

	@Column(name = "AMOUNT")
	Double amount;

	@Column(name = "DATE")
	String date;

	@Column(name = "TRANSACTION_TYPE")
	String transactionType;

	@Column(name = "RECEIVER")
	String receiver;

	@Column(name = "ACCOUNT_NUMBER")
	String accountNumber;

}