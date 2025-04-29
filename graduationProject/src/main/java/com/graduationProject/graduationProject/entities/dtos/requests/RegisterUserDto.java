package com.graduationProject.graduationProject.entities.dtos.requests;

import lombok.Data;

@Data
public class RegisterUserDto {	
	
	private String name;

	private String surname;

	private String email;

	private String password;

	private String tcNumber;

	private int birthYear;

	private String phoneNumber;

	private String address;

}
