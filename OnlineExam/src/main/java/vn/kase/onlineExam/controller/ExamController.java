package vn.kase.onlineExam.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import vn.kase.onlineExam.model.Subject;
import vn.kase.onlineExam.repository.QuestionRepository;
import vn.kase.onlineExam.repository.SubjectRepository;
import vn.kase.onlineExam.viewModel.Exam;
import vn.kase.onlineExam.viewModel.EditorVM;
import vn.kase.onlineExam.viewModel.QuestionVM;

@RestController
public class ExamController {
	
	@Autowired
	private QuestionRepository questionRepo;
	@Autowired
	private SubjectRepository subjectRepo;
	
	@GetMapping("/exam/editor")
	public ModelAndView createExam() {
		return new ModelAndView("exam/editor", "exam", new EditorVM());
	}
	
	@PostMapping("/exam/submitExam")
	public ModelAndView submitExam(@Valid @ModelAttribute(value = "exam") EditorVM exam, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ModelAndView("exam/editor", "exam", exam);
		}
		
		System.out.println(exam.toString());
		return new ModelAndView("exam/editor", "exam", exam);
	}
		
}

