package com.graduationProject.graduationProject.entities.dtos.responses;

import lombok.Data;

@Data
public class LoginResponse {
	private String token;

    private long expiresIn;

    

}
