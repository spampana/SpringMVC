package com.journaldev.spring;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.journaldev.spring.controller.EmpRestURIConstants;
import com.journaldev.spring.model.Employee;
import com.journaldev.spring.model.Student;

public class TestSpringRestExample1 {

	
public static final String SERVER_URI = "http://localhost:8080/SpringRestExample";
	
	public static void main(String args[]){
		
		testGetDummyStudent();
		System.out.println("*****");
		testCreateStudent();
		System.out.println("*****");
		//testGetStudent();
		System.out.println("*****");
		//testGetAllStudent();
	}

	
	
	
	private static void testGetAllStudent() {
		RestTemplate restTemplate = new RestTemplate();
		//we can't get List<Employee> because JSON convertor doesn't know the type of
		//object in the list and hence convert it to default JSON object type LinkedHashMap
		List<LinkedHashMap> emps = restTemplate.getForObject(SERVER_URI+EmpRestURIConstants.STUDENT_GET_ALL_EMP, List.class);
		System.out.println(emps.size());
		for(LinkedHashMap map : emps){
			System.out.println("ID="+map.get("id")+",Name="+map.get("name")+",CreatedDate="+map.get("createdDate"));;
		}
	}

	private static void testCreateStudent() {
		RestTemplate restTemplate = new RestTemplate();
		Student emp = new Student();
		emp.setId(1);
		emp.setName("Pankaj Kumar");
		Student response = restTemplate.postForObject(SERVER_URI+EmpRestURIConstants.STUDENT_CREATE_EMP, emp, Student.class);
		printEmpData(response);
	}

	private static void testGetStudent() {
		RestTemplate restTemplate = new RestTemplate();
		Student emp = restTemplate.getForObject(SERVER_URI+"/rest/emp/1", Student.class);
		printEmpData(emp);
	}

	private static void testGetDummyStudent() {
		RestTemplate restTemplate = new RestTemplate();
		Student emp = restTemplate.getForObject(SERVER_URI+EmpRestURIConstants.STUDENT_DUMMY_EMP, Student.class);
		printEmpData(emp);
	}
	
	public static void printEmpData(Student emp){
		System.out.println("ID="+emp.getId()+",Name="+emp.getName()+",CreatedDate="+emp.getCreatedDate());
	}
}
