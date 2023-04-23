package com.example.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

/**
 * @author YoussefMahmoud
 * @created Apr 23, 2023-4:23:13 PM
 */

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "ACCOUNT")
public class AccountEntity {

	@Column(name = "ACCOUNT_NUMBER")
	@Id
	String accountNumber;

	@Column(name = "CURRENCY")
	String currency;

	@Column(name = "TYPE")
	String type;

	@Column(name = "BALANCE")
	Double balance;

	@Column(name = "USER_NID")
	String userNid;

	@Column(name = "HAS_IB")
	String hasIb;

	@Column(name = "PASSWORD")
	String password;

}