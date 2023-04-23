package com.example.demo.models;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

/**
 * @author YoussefMahmoud
 * @created Apr 23, 2023-4:32:12 PM
 */

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequest {

	String accountNumber;
	String password;

}