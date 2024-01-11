package com.medilabosolutions.back.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Patient {

	int id;
	String firstname;
	String name;
	LocalDate dateOfBirth;
	String gender;
	String address;
	String phoneNumber;
	public Patient(){
	}
	public Patient(int id,String firstname, String name, LocalDate dateOfBirth, String gender, String address, String phoneNumber) {
		this.id = id;
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
				"id=" + id +
				"firstname='" + firstname + '\'' +
				", name='" + name + '\'' +
				", dateOfBirth=" + dateOfBirth +
				", gender='" + gender + '\'' +
				", address='" + address + '\'' +
				", phoneNumber='" + phoneNumber + '\'' +
				'}';
	}
}
