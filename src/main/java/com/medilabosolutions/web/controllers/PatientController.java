package com.medilabosolutions.web.controllers;

import com.medilabosolutions.repository.PatientRepo;
import com.medilabosolutions.model.Patient;
import com.medilabosolutions.web.exceptions.PatientNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/patients")
public class PatientController {

	@Autowired
	PatientRepo patientRepo;

	//Afficher la liste des patients
	@GetMapping
	public List<Patient> listePatients() {
		List<Patient> patients = patientRepo.findAll();

//		if (patients.isEmpty()) throw new PatientNotFoundException("Aucun patient n'est enregistré");

		return patients;
	}

	//Récupérer un patient par son id
	@GetMapping("/{id}")
	public Patient afficherUnPatient(@PathVariable int id) {
		return patientRepo.findById(id)
				.orElseThrow(() -> new PatientNotFoundException("Le patient correspondant à l'id " + id + " n'existe pas"));
	}

	//Chercher un patient en fonction d'un parametre de recherche
	@GetMapping("/search")
	public List<Patient> searchPatients(@RequestParam String searchType, @RequestParam String value) {
		switch(searchType) {
			case "name":
				return patientRepo.findByName(value);
			case "firstname":
				return patientRepo.findByFirstname(value);
			case "yearOfBirth":
				int year = Integer.parseInt(value);
				return patientRepo.findByYearOfBirth(year);
			default:
				return List.of();
		}
	}

	//Ajouter un nouveau patient
	@PostMapping
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

	//Modifier les caractéristiques d'un patient par son id
	@PutMapping("/update/{id}")
	public ResponseEntity<Patient> updatePatient(@PathVariable int id, @RequestBody Patient updatedPatient) {
		Optional<Patient> optionalExistingPatient = patientRepo.findById(id);

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

	//Supprimer un patient par son id
	@DeleteMapping("/delete/{id}")
	public void retirerUnPatient(@PathVariable int id) {
		 patientRepo.deleteById(id);
	}
}

