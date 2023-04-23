package com.example.demo.models;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

/**
 * @author YoussefMahmoud
 * @created Apr 23, 2023-4:33:54 PM
 */

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {

	String nid;
	String name;
	String birthDate;
	String address;
	String maritalStatus;
	String mobileNumber;
	String email;
	String nationality;

}