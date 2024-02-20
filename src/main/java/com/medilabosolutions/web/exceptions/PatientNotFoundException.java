package com.medilabosolutions.web.exceptions;

public class PatientNotFoundException extends RuntimeException {

	public PatientNotFoundException(String message){
		super(message);
	}
}
