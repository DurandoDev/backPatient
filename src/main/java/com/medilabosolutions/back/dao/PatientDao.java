package com.medilabosolutions.back.dao;

import com.medilabosolutions.back.model.Patient;

import java.util.List;

public interface PatientDao {
	List<Patient> findAll();
	Patient findById(int id);

	Patient save(Patient patient);
}
