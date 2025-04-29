package com.graduationProject.graduationProject.business.abstracts;

import java.util.List;

import com.graduationProject.graduationProject.entities.concretes.Bed;
import com.graduationProject.graduationProject.entities.dtos.responses.BedWithReservationInfoDto;

public interface BedService {

	Bed createBed(int bedNumber);
	
	List<BedWithReservationInfoDto> getAllBedsWithReservationInfo();

}
