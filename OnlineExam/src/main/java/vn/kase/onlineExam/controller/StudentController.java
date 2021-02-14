package vn.kase.onlineExam.controller;


import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import vn.kase.onlineExam.model.Mark;
import vn.kase.onlineExam.model.Subject;
import vn.kase.onlineExam.model.User;
import vn.kase.onlineExam.services.MarkService;
import vn.kase.onlineExam.services.QuestionService;
import vn.kase.onlineExam.services.SubjectService;



@Controller
@RequestMapping("/students")
public class StudentController {
	@Autowired
	SubjectService subjectService;
	@Autowired
	QuestionService questionService;
	@Autowired
	MarkService markService;
	
	@RequestMapping("/viewExam")
	public String viewExam(ModelMap model,@RequestParam(value = "subjectId", required = true) int subjectId){
		Optional<Subject> subject = subjectService.findById(subjectId);
		if(subject.isPresent())
		{
			model.addAttribute("subject", subject);
			return "students/subjectWillDo";
		}
		return "students/viewStudent";
	}
	
	@GetMapping("/doExam")
	public String doExam(Model model,Subject subject,HttpServletRequest request, RedirectAttributes redirect) {	
		model.addAttribute("subject", subject);
		request.getSession().setAttribute("subject", subject);
		request.getSession().setAttribute("listQuestion", null);
		return "/doExamApp/index";
	}
	
	@GetMapping("/results")
	public String result(ModelMap model, HttpSession session) {
		User user =(User) session.getAttribute("student");
		List<Mark> listMarks = markService.findAllByStudentId(user.getId());
		for (Mark mark : listMarks) {
			Subject subject = subjectService.findById(mark.getSubjectId()).get();
			mark.setSubject(subject);
			/*
			 * if(mark.getSubject().getEndDate().compareTo(new Date())<0); {
			 * mark.setMarks(0); markService.save(mark); }
			 */
		}
		model.addAttribute("listMarks", listMarks);
		return "students/results";
	}
}
