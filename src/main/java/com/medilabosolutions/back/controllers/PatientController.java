package com.medilabosolutions.back.controllers;

import com.medilabosolutions.back.repository.PatientRepo;
import com.medilabosolutions.back.model.Patient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
public class PatientController {

	private final PatientRepo patientRepo;

	public PatientController(PatientRepo patientRepo){
		this.patientRepo = patientRepo;
	}

	@GetMapping("/Patients")
	public List<Patient> listePatients() {
		return patientRepo.findAll();
	}

	@GetMapping("/Patients/{id}")
	public Patient afficherUnPatient(@PathVariable int id) {
		return patientRepo.findById(id);
	}

	@PostMapping("/Patients")
	public ResponseEntity<Patient> ajouterPatient(@RequestBody Patient patient) {
		Patient patientAdded = patientRepo.save(patient);
		if (Objects.isNull(patientAdded)){
			return ResponseEntity.noContent().build();
		}
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(patientAdded.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping("/Patients/{id}")
	public ResponseEntity<Patient> updatePatient(@PathVariable int id, @RequestBody Patient updatedPatient) {
		Optional<Patient> optionalExistingPatient = Optional.ofNullable(patientRepo.findById(id));

		if (optionalExistingPatient.isPresent()) {
			Patient existingPatient = optionalExistingPatient.get();

			existingPatient.setFirstname(updatedPatient.getFirstname());
			existingPatient.setName(updatedPatient.getName());
			existingPatient.setDateOfBirth(updatedPatient.getDateOfBirth());
			existingPatient.setGender(updatedPatient.getGender());
			existingPatient.setAddress(updatedPatient.getAddress());
			existingPatient.setPhoneNumber(updatedPatient.getPhoneNumber());

			patientRepo.save(existingPatient);

			return ResponseEntity.ok(existingPatient);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/Patients/{id}")
	public void retirerUnPatient(@PathVariable int id) {
		 patientRepo.deleteById(id);
	}
}

