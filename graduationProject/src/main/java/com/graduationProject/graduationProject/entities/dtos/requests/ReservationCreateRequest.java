package com.graduationProject.graduationProject.entities.dtos.requests;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ReservationCreateRequest {

	private Long bedId;
    private LocalDate startingDate;
    private LocalDate finishingDate;
	
}
