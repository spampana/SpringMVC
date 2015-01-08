package com.javacodegeeks.snippets.enterprise.form;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javacodegeeks.snippets.enterprise.form.model.Form;

@Controller
@RequestMapping("/form.htm")
public class FormController {

    @Autowired
    @Qualifier("formValidator")
    private Validator validator;
    
    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }
	
	@RequestMapping(method = RequestMethod.GET)
	public String initForm(Model model) {
		Form form = new Form();
		form.setHiddenMessage("JavaCodeGeek");
		model.addAttribute("form", form);
		initModelList(model);
		return "form";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String submitForm(Model model, @Validated Form form, BindingResult result) {
		model.addAttribute("form", form);
		String returnVal = "successForm";
		if(result.hasErrors()) {
			initModelList(model);
			returnVal = "form";
		} else {
			model.addAttribute("form", form);
		}		
		return returnVal;
	}

	private void initModelList(Model model) {
		List<String> courses = new ArrayList<String>();
		courses.add("Maths");
		courses.add("Physics");
		courses.add("Geometry");
		courses.add("Algebra");
		courses.add("Painting");
		model.addAttribute("courses", courses);
		
		List<String> genders = new ArrayList<String>();
		genders.add("Male");
		genders.add("Female");
		model.addAttribute("genders", genders);
		
		List<String> tutors = new ArrayList<String>();
		tutors.add("Mrs Smith");
		tutors.add("Mr Johnson");
		tutors.add("Mr Clarks");
		model.addAttribute("tutors", tutors);
		model.addAttribute("genders", genders);
	}
}
