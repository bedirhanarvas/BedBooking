package com.graduationProject.graduationProject.entities.dtos.responses;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BedWithReservationInfoDto {

	private Long bedId;
    private int bedNumber;
    private UserBasicDto user;
	
}
