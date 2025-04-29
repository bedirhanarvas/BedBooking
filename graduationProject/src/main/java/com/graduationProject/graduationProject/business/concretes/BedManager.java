package com.graduationProject.graduationProject.business.concretes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.graduationProject.graduationProject.business.abstracts.BedService;
import com.graduationProject.graduationProject.dataAccess.abstracts.BedDao;
import com.graduationProject.graduationProject.entities.concretes.Bed;
import com.graduationProject.graduationProject.entities.concretes.Reservation;
import com.graduationProject.graduationProject.entities.concretes.User;
import com.graduationProject.graduationProject.entities.dtos.responses.BedWithReservationInfoDto;
import com.graduationProject.graduationProject.entities.dtos.responses.UserBasicDto;

@Service
public class BedManager implements BedService{
	
	private final BedDao bedDao;

	public BedManager(BedDao bedDao) {
		this.bedDao=bedDao;
	}
	
	@Override
	public Bed createBed(int bedNumber) {
		
		 if (bedDao.existsByBedNumber(bedNumber)) {
	            throw new IllegalArgumentException("Bu yatak numarası zaten var!");
	        }
		 Bed newBed = new Bed();
	        newBed.setBedNumber(bedNumber);
	        
	        
	     return bedDao.save(newBed);
	}
	
	@Override
	public List<BedWithReservationInfoDto> getAllBedsWithReservationInfo() {
	    List<Bed> beds = bedDao.findAll();
	    List<BedWithReservationInfoDto> bedDtos = new ArrayList<>();
	    LocalDate today = LocalDate.now();

	    for (Bed bed : beds) {
	    	UserBasicDto userDto = null;

	        if (bed.getReservations() != null && !bed.getReservations().isEmpty()) {
	            for (Reservation reservation : bed.getReservations()) {
	            	if ((today.isEqual(reservation.getStartingDate()) || today.isAfter(reservation.getStartingDate())) &&
	                        (today.isEqual(reservation.getFinishingDate()) || today.isBefore(reservation.getFinishingDate()))) {

	                        User user = reservation.getUser();
	                        userDto = new UserBasicDto(user.getName(), user.getSurname(), user.getTcNumber());
	                        break;
	                    }
	            }
	        }

	        bedDtos.add(new BedWithReservationInfoDto(bed.getId(),bed.getBedNumber(),userDto));// null veya dolu User nesnesi
	    }

	    return bedDtos;
	}

}
