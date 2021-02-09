package vn.kase.onlineExam.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import vn.kase.onlineExam.model.Mark;
import vn.kase.onlineExam.model.Question;
import vn.kase.onlineExam.model.Subject;
import vn.kase.onlineExam.model.User;
import vn.kase.onlineExam.services.MarkService;
import vn.kase.onlineExam.services.SubjectService;
import vn.kase.onlineExam.services.UserService;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Controller
@RequestMapping("/")
@SessionAttributes
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private MarkService markService;
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private JavaMailSender javaMailSender;
	
	@GetMapping("/")
	public String login(ModelMap model, HttpSession session) {
		User user = new User();
		model.addAttribute("user", user);
		if(session.getAttribute("student") != null) {
			model.addAttribute("question", new Question());
			List<Integer> listInt = new ArrayList<Integer>();
			List<Mark> listMark = new ArrayList<Mark>();
			User user1 =(User) session.getAttribute("student");
			listMark= markService.findAllByStudentId(user1.getId());
			for (Mark mark : listMark) {
				if(mark.getMarks()== null)
					listInt.add(mark.getSubjectId());
			}
			List<Subject> listSubject = subjectService.findAllById(listInt);
			model.addAttribute("listSubject", listSubject);
			return "students/viewStudent";
		}
		if(session.getAttribute("admin") != null) {
			return "admin/viewAdmin";
		}
		if(session.getAttribute("staff") != null) {
			return "staff/viewStaff";
		}
		
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
			return login(model,session);
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
				if(mark.getMarks()== null)
				listInt.add(mark.getSubjectId());
			}
			List<Subject> listSubject = subjectService.findAllById(listInt);
			model.addAttribute("listSubject", listSubject);
			return "students/viewStudent";
		}
		return login(model,session);
	}
	
	@GetMapping("/logout")
	public String logout(ModelMap model,HttpSession session) {
		if(session.getAttribute("student") != null) {
			session.removeAttribute("student");
		}
		if(session.getAttribute("admin") != null) {
			session.removeAttribute("admin");
		}
		if(session.getAttribute("staff") != null) {
			session.removeAttribute("staff");
		}
		User user = new User();
		model.addAttribute("user", user);
		return "login";
	}
	
	@GetMapping("/forgetEmail")
	public String forgetEmail() {
		return "forgetEmail";
	}
	
	@PostMapping("/getEmail")
	public String getEmail(ModelMap model,@RequestParam String email) {
		User user = userService.findByEmail(email);
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(user.getEmail());
		msg.setSubject("Retrieve Your Password Online Exam");
		msg.setText("Your password : "+ user.getPass());
		javaMailSender.send(msg);
		model.addAttribute("message", "Password has been sent to your mail!");
		return "forgetEmail";
	}
}
