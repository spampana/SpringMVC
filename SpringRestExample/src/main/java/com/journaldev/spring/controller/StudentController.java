package com.journaldev.spring.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.journaldev.spring.model.Employee;
import com.journaldev.spring.model.Student;

/**
 * Handles requests for the Employee service.
 */
@RestController
public class StudentController {
	
	private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
	
	//Map to store employees, ideally we should use database
	Map<Integer, Student> stuData = new HashMap<Integer, Student>();
	
	@RequestMapping(value = EmpRestURIConstants.STUDENT_DUMMY_EMP, method = RequestMethod.GET,headers = "Accept=application/xml")
	public Student getDummyStudent() {
		logger.info("Start getDummyStudent");
		Student stu = new Student();
		stu.setId(9999);
		stu.setName("Dummy");
		stu.setCreatedDate(new Date());
		
		stuData.put(9999, stu);
		return stu;
	}
	
	@RequestMapping(value = EmpRestURIConstants.STUDENT_GET_EMP, method = RequestMethod.GET,headers = "Accept=application/xml")
	public Student getStudent(@PathVariable("id") int stuId) {
		logger.info("Start getStudent. ID="+stuId);
		
		return stuData.get(stuId);
	}
	
	@RequestMapping(value = EmpRestURIConstants.STUDENT_GET_ALL_EMP, method = RequestMethod.GET,headers = "Accept=application/xml")
	public List<Student> getAllStudnets() {
		logger.info("Start getAllEmployees.");
		List<Student> studs = new ArrayList<Student>();
		Set<Integer> empIdKeys = stuData.keySet();
		for(Integer i : empIdKeys){
			studs.add(stuData.get(i));
		}
		return studs;
	}
	
	@RequestMapping(value = EmpRestURIConstants.STUDENT_CREATE_EMP, method = RequestMethod.POST,headers = "Accept=application/xml")
	public Student createStudent(Student stu) {
		logger.info("Start createEmployee.");
		stu.setCreatedDate(new Date());
		stuData.put(stu.getId(), stu);
		return stu;
	}
	
	@RequestMapping(value = EmpRestURIConstants.STUDENT_DELETE_EMP, method = RequestMethod.PUT,headers = "Accept=application/xml")
	public  Student deleteStudent(@PathVariable("id") int studId) {
		logger.info("Start deleteStudent.");
		Student emp = stuData.get(studId);
		stuData.remove(studId);
		return emp;
	}
	
}
