package com.javacodegeeks.snippets.enterprise.form.validator;

import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.javacodegeeks.snippets.enterprise.form.model.Form;

public class FormValidator implements Validator {

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	public boolean supports(Class<?> paramClass) {
		return Form.class.equals(paramClass);
	}

	public void validate(Object obj, Errors errors) {
		Form form = (Form) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "valid.name");
		if(!form.getEmail().matches(EMAIL_PATTERN)) {
			errors.rejectValue("email","valid.email");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gender", "valid.gender");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "valid.password");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwordConf", "valid.passwordConf");
		if (!form.getPassword().equals(form.getPasswordConf())) {
			errors.rejectValue("passwordConf", "valid.passwordConfDiff");
		}		
		List<String> courses = form.getCourses();
		if (courses == null || courses.size() < 2) {
			errors.rejectValue("courses", "valid.courses");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tutor", "valid.tutor");
	}
}
