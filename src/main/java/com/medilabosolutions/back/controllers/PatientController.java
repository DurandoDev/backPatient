package com.medilabosolutions.back.controllers;

import com.medilabosolutions.back.dao.PatientDao;
import com.medilabosolutions.back.model.Patient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PatientController {
	private final PatientDao patientDao;

	public PatientController(PatientDao patientDao){
		this.patientDao = patientDao;
	}

	@GetMapping("/Patients")
	public List<Patient> listeProduits() {
		return patientDao.findAll();
	}

	@GetMapping(value = "/Patients/{id}")
	public Patient afficherUnPatient(@PathVariable int id) {
		return patientDao.findById(id);
	}
}

