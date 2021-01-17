package vn.kase.onlineExam.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import vn.kase.onlineExam.model.Question;
import vn.kase.onlineExam.model.Subject;
import vn.kase.onlineExam.repository.QuestionRepository;
import vn.kase.onlineExam.repository.SubjectRepository;
import vn.kase.onlineExam.viewModel.Exam;

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

		Exam exam = new Exam();
		// Mock data
		Subject subject = new Subject();
		subject.setSubjectName("Math");
		List<Question> questions = new ArrayList<Question>();
		exam.setQuestions(questions);
		exam.setSubject(subject);
		// End mock data

		modelView.addObject(exam);
		return modelView;
	}
	
	@PostMapping("/exam/submitExam")
	public String submitExam(@ModelAttribute(value = "exam") Exam exam) {
		System.out.println(exam.toString());
		return "success";
	}
		
}

