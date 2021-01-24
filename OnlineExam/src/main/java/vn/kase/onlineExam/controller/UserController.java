package vn.kase.onlineExam.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.kase.onlineExam.model.Mark;
import vn.kase.onlineExam.model.Question;
import vn.kase.onlineExam.model.Subject;
import vn.kase.onlineExam.model.User;
import vn.kase.onlineExam.repository.MarkService;
import vn.kase.onlineExam.services.QuestionService;
import vn.kase.onlineExam.services.SubjectService;
import vn.kase.onlineExam.services.UserService;

@Controller
@RequestMapping("/")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private QuestionService questionService;
	@Autowired
	private MarkService markService;
	@Autowired
	private SubjectService subjectService;
	
	@GetMapping("/")
	public String login(ModelMap model) {
		User user = new User();
		model.addAttribute("user", user);
		return "login";
	}		
	@PostMapping("/viewInfo")
	public String check(ModelMap model,User user, HttpSession session) {
		String username = user.getUsername();
		String pass = user.getPass();
		User user1 = userService.findByUsername(username);
		model.addAttribute("question", new Question());
		if(user1 == null || !(pass.equals(user1.getPass())))
		{
			model.addAttribute("message", "*Username or password incorect !");
			return login(model);
		}
		if(user1.getRoles() == 1)
		{
			session.setAttribute("admin", user1);
			return "admin/viewAdmin";
		}
		if(user1.getRoles()  == 2)
		{
			session.setAttribute("staff", user1);
			return "staff/viewStaff";
		}
		if(user1.getRoles()  == 3)
		{
			session.setAttribute("student", user1);
			List<Integer> listInt = new ArrayList<Integer>();
			List<Mark> listMark = new ArrayList<Mark>();
			listMark= markService.findAllByStudentId(user1.getId());
			for (Mark mark : listMark) {
				listInt.add(mark.getSubjectId());
			}
			List<Subject> listSubject = subjectService.findAllById(listInt);
			model.addAttribute("listSubject", listSubject);
			return "students/viewStudent";
		}
		return login(model);
	}
}
