package com.medilabosolutions.back.repository;

import com.medilabosolutions.back.dao.PatientDao;
import com.medilabosolutions.back.model.Patient;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PatientRepo implements PatientDao {
	public static List<Patient> patients = new ArrayList<>();

	static {
		patients.add(new Patient(1, "Titi","TitiName", LocalDate.of(2001, 1, 1),"Male","addressTiti","0101010101"));
		patients.add(new Patient(2, "Tata","TataName",LocalDate.of(2001, 1, 1),"Female","addressTata","0101010101"));
		patients.add(new Patient(3, "Tutu","TutuName", LocalDate.of(2001, 1, 1),"Male","addressTutu","0101010101"));
	}
	@Override
	public List<Patient> findAll() {
		return patients;
	}

	@Override
	public Patient findById(int id) {
		for (Patient patient : patients){
			if (patient.getId() == id){
				return patient;
			}
		}
		return null;
	}

	@Override
	public Patient save(Patient patient) {
		patients.add(patient);
		return patient;
	}
}
