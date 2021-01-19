package vn.kase.onlineExam.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.kase.onlineExam.model.Subject;
import vn.kase.onlineExam.services.SubjectService;

@Controller
@RequestMapping("/subject")
public class SubjectController {
	@Autowired
	SubjectService subjectService;
	
	@GetMapping("/add")
	public String add(ModelMap model) {
		model.addAttribute("subject", new Subject());
		return "exam/createQuestion";
	}
	
	@PostMapping("/saveOrUpdate")
	public String saveOrUpdate(Subject subject) {
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
//	@ModelAttribute("listSubjects")
//	public List<Subject> getAllSubject(){
//		return (List<Subject>) subjectService.findAll();
//	}
//	
//	@GetMapping("/createQuestion")
//	public String createQuestion(){
//		return "/exam/createQuestion";
//	}
//	
//	@PostMapping("/createQuestion")
//	public Subject save(@RequestBody Subject subject) {
//		return subjectService.save(subject);
//	}
}
