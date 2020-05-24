package com.rest.persona;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.rest.person.dao.PersonRepository;
import com.rest.person.model.Person;
import com.rest.person.service.PersonService;

@SpringBootTest
class PersonApplicationTests {

	@Autowired
	PersonRepository personaRepository;

	@Autowired
	PersonService personaService;
	
	Person person1;
	Person person2;
	
	
    @BeforeEach
    public void beforeEachTestMethod() {
        System.out.println("Invoked before each test method");
		person1 = new Person();
		person1.setId(1);
		person1.setName("Juanita");
		person1.setSurname("Velez");
		person1.setAge(30);
		person1.setGender('F');
		person2 = new Person();
		person2.setId(2);
		person2.setName("Jesus");
		person2.setSurname("Nazareno");
		person2.setAge(33);
		person2.setGender('M');
		
		personaService.addPerson(person1);
		personaService.addPerson(person2);
    }

	@Test
	public void testFindAll() {

		List<Person> result = personaService.findAll();

		// then
		assertThat(result.size()).isEqualTo(2);

		assertThat(result.get(0).getName()).isEqualTo(person1.getName());

		assertThat(result.get(1).getName()).isEqualTo(person2.getName());

	}

	@Test
	public void testAddPerson() {

		Person person3 = new Person();
		person3.setId(3);
		person3.setName("Juan");
		person3.setSurname("Bautista");
		person3.setAge(30);
		person3.setGender('M');
		personaService.addPerson(person3);
		assertNotNull(personaRepository.findById(3));
	}

	@Test
	public void testUpdatePerson() {
		
		Person person = new Person();
		person.setId(1);
		person.setName("Julio");
		person.setSurname("Velez");
		person.setAge(30);
		person.setGender('F');

		Person oldPerson= personaRepository.findById(1).get();
		
		personaService.updatePerson(person);

		assertNotEquals(oldPerson.getName(), person.getName());

	}

	@Test
	public void testDeletePerson() {

		assertNotNull(personaRepository.findById(3));

		personaService.deletePerson(2);

		assertFalse(personaRepository.findById(3).isPresent());

	}

}
