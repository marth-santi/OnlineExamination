package vn.kase.onlineExam.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vn.kase.onlineExam.model.Question;
import vn.kase.onlineExam.model.Subject;
import vn.kase.onlineExam.services.QuestionService;

@Controller
@RequestMapping("/question")
public class QuestionController {
	@Autowired
	private QuestionService questionService;	
	
	@ModelAttribute("listSubjects")
	public List<Subject> getAllSubject(){
		return questionService.findAllSubjects();
	}
	
	@GetMapping("/add")
	public String add(ModelMap model) {
		Question question = new Question();
		model.addAttribute("question", question);
		return "question/createQuestion";
	}
	
	@PostMapping("/save")
	public String addList(ModelMap model,Question question) {
		if(question.getId() != null && question.getId()>0)
			model.addAttribute("message", "Updated");
		else
			model.addAttribute("message", "Inserted");
		questionService.save(question);
		return "question/createQuestion";
	}

	@GetMapping("/edit/{id}")
	public String edit(ModelMap model, @PathVariable(name="id") Integer id) {
		Optional<Question> optQuestion = questionService.findById(id);
		if(optQuestion.isPresent()) {
			model.addAttribute("question", optQuestion.get());
		}
		else {
			return review(model, id);
		}
		return "question/createQuestion";
	}
	int idReview;
	@GetMapping("/delete/{id}")
	public String delete(ModelMap model, @PathVariable(name="id") Integer id) {
		Optional<Question> optQuestion = questionService.findById(id);
		if(optQuestion.isPresent())
			questionService.deleteById(id);
		return review(model, idReview);
	}
	
	@RequestMapping("/review")
	public String review(ModelMap model,@RequestParam(defaultValue = "1") Integer subjectId) {
		List<Question> listReview = questionService.findAllBySubjectId(subjectId);
		model.addAttribute("listReview", listReview);
		idReview = subjectId;
		return "question/review";
	}
}
