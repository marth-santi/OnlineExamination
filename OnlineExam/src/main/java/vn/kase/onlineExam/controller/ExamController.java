package vn.kase.onlineExam.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ExamController {

	@GetMapping("/exam/editor")
	public ModelAndView CreateExam() {
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("exam/editor");
		return modelView;
	}
	
}
