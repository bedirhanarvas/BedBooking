package com.graduationProject.graduationProject.entities.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBasicDto {

	private String name;
    private String surname;
    private String tcNumber;
}
