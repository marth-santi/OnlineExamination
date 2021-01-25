package vn.kase.onlineExam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.kase.onlineExam.model.Question;
import vn.kase.onlineExam.model.Subject;
import vn.kase.onlineExam.services.QuestionService;
import vn.kase.onlineExam.services.SubjectService;

@Controller
@RequestMapping("/question")
public class QuestionController {
	@Autowired
	private QuestionService questionService;
	@Autowired
	private SubjectService subjectService;
	
	@ModelAttribute("listSubjects")
	public List<Subject> getAllSubject(){
		return questionService.findAllSubjects();
	}
	
	@GetMapping("/review/{subjectId}")
	public String review(ModelMap model,@PathVariable(name="subjectId" ) Integer subjectId) {
			List<Question> listReview = questionService.findAllBySubjectId(subjectId);
			model.addAttribute("listReview", listReview);
			model.addAttribute("subject", subjectService.findById(subjectId).orElse(null));
			return "question/review";
	}
}
