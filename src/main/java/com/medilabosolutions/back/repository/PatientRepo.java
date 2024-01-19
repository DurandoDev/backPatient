package com.medilabosolutions.back.repository;

import com.medilabosolutions.back.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepo extends JpaRepository<Patient, Integer> {
	List<Patient> findAll();
	Patient findById(int id);

	Patient save(Patient patient);
}
