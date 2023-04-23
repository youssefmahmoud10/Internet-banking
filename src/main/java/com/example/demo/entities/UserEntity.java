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
 * @created Apr 23, 2023-4:20:12 PM
 */

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "USER")
public class UserEntity {

	@Column(name = "NID")
	@Id
	String nid;

	@Column(name = "NAME")
	String name;

	@Column(name = "BIRTH_DATE")
	String birthDate;

	@Column(name = "ADDRESS")
	String address;

	@Column(name = "MARITAL_STATUS")
	String maritalStatus;

	@Column(name = "MOBILE_NUMBER")
	String mobileNumber;

	@Column(name = "EMAIL")
	String email;

	@Column(name = "NATIONALITY")
	String nationality;

}