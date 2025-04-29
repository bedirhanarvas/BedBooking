package com.graduationProject.graduationProject.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.graduationProject.graduationProject.business.abstracts.BedService;
import com.graduationProject.graduationProject.entities.concretes.Bed;
import com.graduationProject.graduationProject.entities.dtos.responses.BedWithReservationInfoDto;

@RestController
@RequestMapping("/api/beds")
public class BedController {
	
	   private final BedService bedService;

	    public BedController(BedService bedService) {
	        this.bedService = bedService;
	    }

	    @PostMapping("/create")
	    public ResponseEntity<Bed> createBed(@RequestParam int bedNumber) {
	        return ResponseEntity.ok(bedService.createBed(bedNumber));
	    }

	    @GetMapping("/getAllWithReservations")
	    public ResponseEntity<List<BedWithReservationInfoDto>> getAllBedsWithReservations() {
	        return ResponseEntity.ok(bedService.getAllBedsWithReservationInfo());
	    }
}
