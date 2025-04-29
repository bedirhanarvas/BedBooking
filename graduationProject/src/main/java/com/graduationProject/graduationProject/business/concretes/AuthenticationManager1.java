package com.graduationProject.graduationProject.business.concretes;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.graduationProject.graduationProject.business.abstracts.AuthenticationService;
import com.graduationProject.graduationProject.dataAccess.abstracts.UserDao;
import com.graduationProject.graduationProject.entities.concretes.User;
import com.graduationProject.graduationProject.entities.dtos.requests.LoginUserDto;
import com.graduationProject.graduationProject.entities.dtos.requests.RegisterUserDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationManager1 implements AuthenticationService{
	
	private final UserDao userDao;
    
    private final PasswordEncoder passwordEncoder;
    
    private final org.springframework.security.authentication.AuthenticationManager authenticationManager;

	@Override
	public User signup(RegisterUserDto input) {
		User user = new User();
        user.setTcNumber(input.getTcNumber());
        user.setName(input.getName());
        user.setSurname(input.getSurname());
        user.setEmail(input.getEmail());
        user.setPhoneNumber(input.getPhoneNumber());
        user.setBirthYear(input.getBirthYear());
        user.setAddress(input.getAddress());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        
        return userDao.save(user);
	}

	@Override
	public User authenticate(LoginUserDto input) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(input.getEmail(), input.getPassword()));
		return userDao.findByEmail(input.getEmail())
                .orElseThrow();
	}

  
    

}
