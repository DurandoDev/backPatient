package com.medilabosolutions.back.web.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BackController {
	@GetMapping("/Patients")
	public String listePatients() {
		return "Un exemple de patient";
	}

	@GetMapping("/Patients/{id}")
	public String afficherUnPatient(@PathVariable int id) {
		return "Vous avez demandé un patient avec l'id  " + id;
	}
}

