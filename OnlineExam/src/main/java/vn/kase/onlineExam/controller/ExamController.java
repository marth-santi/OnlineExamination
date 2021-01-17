package vn.kase.onlineExam.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import vn.kase.onlineExam.repository.QuestionRepository;
import vn.kase.onlineExam.repository.SubjectRepository;

@RestController
public class ExamController {
	
	@Autowired
	private QuestionRepository questionRepo;
	@Autowired
	private SubjectRepository subjectRepo;
	
	@GetMapping("/exam/editor")
	public ModelAndView createExam() {
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("exam/editor");
		return modelView;
	}
	
		
}

