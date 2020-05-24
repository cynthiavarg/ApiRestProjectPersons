package com.rest.person.dto;

import java.io.Serializable;

public class PersonDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4257170122458000384L;

	private int id;
	
	private String name;
	
	private String surname;
	
	private int age;
	
	private char gender;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}
	
	

	

}
