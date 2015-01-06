package org.krams.tutorial.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.krams.tutorial.domain.Person;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service for processing Persons. 
 */
@Service("personService")
@Transactional
public class PersonService {

	protected static Logger logger = Logger.getLogger("service");
	
	private HashMap<Integer, Person> database = new HashMap<Integer, Person>();
	
	/**
	 * Initialize a list of persons
	 */
	private void init() {
		// New person
		Person person = new Person();
		person.setId(1);
		person.setFirstName("John");
		person.setLastName("Smith");
		person.setMoney(1500.00);
		
		// Add to list
		database.put(1, person);
		
		// New person
		person = new Person();
		person.setId(2);
		person.setFirstName("Jane");
		person.setLastName("Adams");
		person.setMoney(1000.00);
		
		// Add to list
		database.put(2, person);
		
		// New person
		person = new Person();
		person.setId(3);
		person.setFirstName("Mike");
		person.setLastName("Polaski");
		person.setMoney(2000.00);
		
		// Add to list
		database.put(3, person);
	}
	
	public PersonService() {
		// Initialize dummy database
		init();
	}
	
	/**
	 * Retrieves all persons
	 * <p>
	 * All registered users can access this
	 * 
	 * @return a list of persons
	 */
	@Secured("ROLE_USER")
	public List<Person> getAll() {
		logger.debug("Retrieving all persons");
		
		// Initialize our array
		List<Person> persons = new ArrayList<Person>();
		
		// Iterate the database
		for (Map.Entry<Integer, Person> entry: database.entrySet()) {
			persons.add(entry.getValue());
		}
		
		// Return all persons
		return persons;	
	}
	
	/**
	 * Retrieves a person based on his id
	 * <p>
	 * All registered users can access this
	 * 
	 * @param id the identifier of the person
	 * @return a person
	 */
	@Secured("ROLE_USER")
	public Person get(Integer id) {
		logger.debug("Retrieving person based on his id");
		return database.get(id);
	}

	/**
	 * Edits an existing person
	 * <p>
	 * Only admins can access this method
	 */
	@Secured("ROLE_ADMIN")
	public void edit(Person person) {
		logger.debug("Editing existing person");
		
		// Note this is not the best way to update a data!
		
		// Delete existing user
		database.remove(person.getId());
		// Add updated user
		database.put(person.getId(), person);
	}
	
	
}
