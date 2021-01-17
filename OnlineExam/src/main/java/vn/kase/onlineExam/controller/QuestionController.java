package vn.kase.onlineExam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.kase.onlineExam.model.Question;
import vn.kase.onlineExam.model.Subject;
import vn.kase.onlineExam.services.QuestionService;
import vn.kase.onlineExam.services.SubjectService;

@Controller
@RequestMapping("/exam")
public class QuestionController {
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private QuestionService questionService;
	
	private List<Question> listQ;
	@ModelAttribute("listSubjects")
	public List<Subject> getAllSubject(){
		return (List<Subject>) subjectService.findAll();
	}
	
	@GetMapping("/add")
	public String add(ModelMap model) {
		model.addAttribute("question", new Question());
		return "exam/createQuestion";
	}
	
	@PostMapping("/addList")
	public String addList(ModelMap model,Question question) {
		questionService.save(question);
		model.addAttribute(question);
		return "exam/createQuestion";
	}
	
	@GetMapping("/saveAll")
	public String saveAll() {
		questionService.saveAll(listQ);
		return "exam/createQuestion";
	}
	
	@GetMapping("/edit")
	public String edit(@PathVariable(name="id") Integer id) {
		return "exam/createQuestion";
	}
	
	@GetMapping("/delete")
	public String delete(@PathVariable(name="id") Integer id) {
		return "list";
	}
	
	@RequestMapping("/list")
	public String list() {
		return "list";
	}
	
	@RequestMapping("/find")
	public String find() {
		return "find";
	}
}
