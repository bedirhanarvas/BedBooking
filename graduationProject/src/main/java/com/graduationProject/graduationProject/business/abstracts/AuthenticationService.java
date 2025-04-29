package com.graduationProject.graduationProject.business.abstracts;


import com.graduationProject.graduationProject.entities.concretes.User;
import com.graduationProject.graduationProject.entities.dtos.requests.LoginUserDto;
import com.graduationProject.graduationProject.entities.dtos.requests.RegisterUserDto;

public interface AuthenticationService {

	User signup(RegisterUserDto input);
	User authenticate(LoginUserDto input);
}
