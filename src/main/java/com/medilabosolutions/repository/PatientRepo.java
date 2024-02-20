package com.medilabosolutions.repository;

import com.medilabosolutions.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepo extends JpaRepository<Patient, Integer> {

	List<Patient> findByName(String name);

	List<Patient> findByFirstname(String firstname);

	@Query("SELECT p FROM Patient p WHERE FUNCTION('YEAR', p.dateOfBirth) = ?1")
	List<Patient> findByYearOfBirth(int yearOfBirth);
}
