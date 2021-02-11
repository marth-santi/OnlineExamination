package vn.kase.onlineExam.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.kase.onlineExam.model.Question;
import vn.kase.onlineExam.model.Subject;
import vn.kase.onlineExam.services.QuestionService;
import vn.kase.onlineExam.services.SubjectService;

@Controller
@RequestMapping("/staff")
public class StaffController {
	@Autowired
	SubjectService subjectService;
	@Autowired
	QuestionService questionService;
	
	@GetMapping("/manageExam")
	  public String manageExam(ModelMap model) {
		List<Subject> listSubject = subjectService.findAll();
		for (Subject subject : listSubject) {
			List<Question> questions = questionService.findAllBySubjectId(subject.getId());
			subject.setTotalQuestion(questions.size());
		}
	    model.addAttribute("listSubject", listSubject);
	    return "staff/managerExam";
	  }
	@GetMapping("/viewQuestion/{id}")
	public String edit(ModelMap model, @PathVariable(name="id") Integer id) {
		List<Question> listQuestion = questionService.findAllBySubjectId(id);
		model.addAttribute("listReview", listQuestion);
		model.addAttribute("subject", subjectService.findById(id));
		return "staff/review";
	}
}
