package com.rest.person.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rest.person.dto.PersonDTO;
import com.rest.person.model.Person;
import com.rest.person.service.PersonService;

@RestController

public class PersonRestController {

	@Autowired
	private PersonService personService;

	final Logger logger = LoggerFactory.getLogger(PersonRestController.class);

	/*
	 * GET - http://127.0.0.1:8080/api/persons
	 */

	@RequestMapping("/api/persons")
	public ResponseEntity<List<Person>> findAll() throws Exception {
		logger.info("Getting Persons");
		List<Person> persons = personService.findAll();

		if (persons == null || persons.isEmpty()) {
			return new ResponseEntity<List<Person>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<Person>>(persons, HttpStatus.OK);

	}

	/*
	 * POST- url: http://127.0.0.1:8080/api/persons/
	 */

	/*
	 * { "nombre": "Jose", "apellido": "Martinez", "edad": "35", "sexo": "M" }
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/api/persons")
	public ResponseEntity<PersonDTO> addPerson(@RequestBody PersonDTO personaDTO) throws Exception {
		logger.info("Creating Person");
		Person newPerson = null;

		newPerson = personService.addPerson(this.convertToEntity(personaDTO, newPerson));

		if (newPerson != null) {
			return new ResponseEntity<>(this.convertToDTO(newPerson, personaDTO), HttpStatus.OK);
		}

		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

	}

	/*
	 * PUT- url: http://127.0.0.1:8080/api/persons/
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/api/persons/{id}")
	public ResponseEntity<PersonDTO> updatePerson(@RequestBody PersonDTO personaDTO, @PathVariable int id)
			throws Exception {
		logger.info("Updating Person");
		Person personUpdated;
		Optional<Person> personaToUpdate = personService.getPerson(id);

		if (personaToUpdate.isPresent()) {

			personUpdated = personService.updatePerson(convertToEntity(personaDTO, personaToUpdate.get()));

			if (personUpdated != null) {
				return new ResponseEntity<>(this.convertToDTO(personUpdated, personaDTO), HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}

		}

		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	/*
	 * DELETE - url: http://127.0.0.1:8080/api/persons/1
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/api/persons/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable int id) throws Exception {
		logger.info("Delete Person with id: {} ", id);
		Optional<Person> personaToUpdate = personService.getPerson(id);

		if (personaToUpdate.isPresent()) {
			personService.deletePerson(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}

		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}

	public Person convertToEntity(PersonDTO personaDTO, Person person) {

		Person personConverted = new Person();
		personConverted.setId(personaDTO.getId());
		personConverted.setName(personaDTO.getName());
		personConverted.setSurname(personaDTO.getSurname());
		personConverted.setAge(personaDTO.getAge());
		personConverted.setGender(personaDTO.getGender());

		return personConverted;

	}

	public PersonDTO convertToDTO(Person persona, PersonDTO personaDTO) {

		PersonDTO personConverted = new PersonDTO();
		personConverted.setId(persona.getId());
		personConverted.setName(persona.getName());
		personConverted.setSurname(persona.getSurname());
		personConverted.setAge(persona.getAge());
		personConverted.setGender(persona.getGender());

		return personConverted;

	}
}
