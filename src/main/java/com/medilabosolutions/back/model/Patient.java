package com.medilabosolutions.back.model;

import lombok.Data;

import java.util.Date;

@Data
public class Patient {

	String firstname;
	String name;
	Date dateOfBirth;
	String gender;
	String address;
	String phoneNumber;
	public Patient(){
	}
	public Patient(String firstname, String name, Date dateOfBirth, String gender, String address, String phoneNumber) {
		this.firstname = firstname;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}
	@Override
	public String toString() {
		return "Patient{" +
				"firstname='" + firstname + '\'' +
				", name='" + name + '\'' +
				", dateOfBirth=" + dateOfBirth +
				", gender='" + gender + '\'' +
				", address='" + address + '\'' +
				", phoneNumber='" + phoneNumber + '\'' +
				'}';
	}
}
