package com.rest.person.dao;

import org.springframework.data.repository.CrudRepository;

import com.rest.person.model.Person;

public interface PersonRepository extends CrudRepository<Person, Integer>{
	

}
