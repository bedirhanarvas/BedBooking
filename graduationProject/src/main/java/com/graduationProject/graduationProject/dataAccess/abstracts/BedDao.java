package com.graduationProject.graduationProject.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.graduationProject.graduationProject.entities.concretes.Bed;

public interface BedDao extends JpaRepository<Bed, Long>{

	List<Bed> findAll();
	
	List<Bed> findByIdNotIn(List<Long> ids);

	boolean existsByBedNumber(int bedNumber);
	
}
