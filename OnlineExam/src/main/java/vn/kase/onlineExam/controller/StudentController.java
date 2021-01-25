package vn.kase.onlineExam.controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vn.kase.onlineExam.model.Subject;
import vn.kase.onlineExam.services.SubjectService;



@Controller
@RequestMapping("/students")
public class StudentController {
	@Autowired
	SubjectService subjectService;
	
	@RequestMapping("/viewExam")
	public String viewExam(ModelMap model,@RequestParam(value = "subjectId", required = true) Integer subjectId){
		Optional<Subject> subject = subjectService.findById(subjectId);
		if(subject.isPresent())
		{
			model.addAttribute("subject", subject);
			return "students/subjectWillDo";
		}
		return "students/viewStudent";
	}
}
