package com.rest.person.service;

import java.util.List;
import java.util.Optional;

import com.rest.person.model.Person;

public interface PersonService {
	
	public List<Person> findAll();
	
	public Person addPerson(Person persona);
	
	public Person updatePerson(Person persona);
	
	public Optional<Person> getPerson(int id);
	
	public void deletePerson(int id);

}
