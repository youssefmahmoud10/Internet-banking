package com.example.demo.models;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

/**
 * @author YoussefMahmoud
 * @created Apr 23, 2023-4:36:14 PM
 */

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SaveRegisterRequest {

	String accountNumber;
	String hasIb;
	String password;
	String userNid;

}