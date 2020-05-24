package com.rest.person.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import javax.persistence.RollbackException;
import javax.persistence.TransactionRequiredException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.person.dao.PersonRepository;
import com.rest.person.model.Person;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonRepository personRepository;

	@Override
	public List<Person> findAll() {
		List<Person> personas = new ArrayList<Person>();
		try {
			personRepository.findAll().forEach(personas::add);
		} catch (NoResultException ex) {
			throw new NoResultException(ex.getMessage());
		} catch (PersistenceException ex) {
			throw new PersistenceException(ex.getMessage());
		}
		return personas;

	}
	
	@Override
	public Person addPerson(Person person) {
		try {
			return personRepository.save(person);
		} catch (IllegalArgumentException ex) {
			throw new IllegalArgumentException(ex.getMessage());
		} catch (EntityNotFoundException ex) {
			throw new EntityNotFoundException(ex.getMessage());
		} catch (RollbackException ex) {
			throw new RollbackException(ex.getMessage());
		} catch (TransactionRequiredException ex) {
			throw new TransactionRequiredException(ex.getMessage());
		} catch (EntityExistsException ex) {
			throw new EntityExistsException(ex.getMessage());
		} catch (PersistenceException ex) {
			throw new PersistenceException(ex.getMessage());
		}

	}

	@Override
	public void deletePerson(int id) {
		try {
			personRepository.deleteById(id);
		} catch (IllegalArgumentException ex) {
			throw new IllegalArgumentException(ex.getMessage());
		} catch (EntityNotFoundException ex) {
			throw new EntityNotFoundException(ex.getMessage());
		} catch (PersistenceException ex) {
			throw new PersistenceException(ex.getMessage());
		}

	}

	@Override
	public Person updatePerson(Person person) {
		try {
			return personRepository.save(person);
		} catch (IllegalArgumentException ex) {
			throw new IllegalArgumentException(ex.getMessage());
		} catch (EntityNotFoundException ex) {
			throw new EntityNotFoundException(ex.getMessage());
		} catch (RollbackException ex) {
			throw new RollbackException(ex.getMessage());
		} catch (TransactionRequiredException ex) {
			throw new TransactionRequiredException(ex.getMessage());
		} catch (EntityExistsException ex) {
			throw new EntityExistsException(ex.getMessage());
		} catch (PersistenceException ex) {
			throw new PersistenceException(ex.getMessage());
		}
	}

	@Override
	public Optional<Person> getPerson(int id) {
		Optional<Person> person;
		try {
			person = personRepository.findById(id);
		} catch (IllegalArgumentException ex) {
			throw new IllegalArgumentException(ex.getMessage());
		} catch (EntityNotFoundException ex) {
			throw new EntityExistsException(ex.getMessage());
		} catch (NonUniqueResultException ex) {
			throw new NonUniqueResultException(ex.getMessage());
		} catch (PersistenceException ex) {
			throw new PersistenceException(ex.getMessage());
		}

		return person;
	}

}
