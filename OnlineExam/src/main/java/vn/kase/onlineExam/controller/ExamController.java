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
import vn.kase.onlineExam.viewModel.ExamVM;
import vn.kase.onlineExam.viewModel.QuestionVM;

@RestController
public class ExamController {
	
	@Autowired
	private QuestionRepository questionRepo;
	@Autowired
	private SubjectRepository subjectRepo;
	
	@GetMapping("/exam/editor")
	public ModelAndView createExam() {
		ModelAndView modelView = new ModelAndView("exam/editor");

		ExamVM exam = new ExamVM();
		// Mock data
		// Subject subject = new Subject();
		// subject.setSubjectName("Math");
		// List<QuestionVM> questions = new ArrayList<QuestionVM>();
		// for (int i = 0; i < 2; i++) {
		// 	QuestionVM q = new QuestionVM();
		// 	q.setSubjectId(1)
		// 			.setQuestion("cau hoi " + i)
		// 			.setOp1("A")
		// 			.setOp2("B")
		// 			.setOp3("C")
		// 			.setOp4("D");
		// 	q.setIsCheckedOp1(true);
		// 	q.setIsMultiple(false);
		// 	questions.add(q);
		// }
		// exam.setQuestions(questions);
		// exam.setSubject(subject);
		// End mock data

		modelView.addObject("exam", exam);
		return modelView;
	}
	
	@PostMapping("/exam/submitExam")
	public ModelAndView submitExam(@Valid @ModelAttribute(value = "exam") ExamVM exam, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ModelAndView("exam/editor", "exam", exam);
		}
		
		System.out.println(exam.toString());
		return new ModelAndView("exam/editor", "exam", exam);
	}
		
}

