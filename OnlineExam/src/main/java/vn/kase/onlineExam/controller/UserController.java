package vn.kase.onlineExam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.kase.onlineExam.model.User;
import vn.kase.onlineExam.services.UserService;

@Controller
@RequestMapping("/")
public class UserController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/login")
	public String login(ModelMap model) {
		User user = new User();
		model.addAttribute("user", user);
		return "login";
	}
	
	@PostMapping("/check")
	public String check(ModelMap model,User user) {
		String username = user.getUsername();
		String pass = user.getPass();
		int role = user.getRole();
		User user1 = userService.findByUsername(username);
		if(user1 == null || !(pass.equals(user1.getPass())) || !(role==user1.getRole()))
		{
			model.addAttribute("message", "Login fail");
			return login(model);
		}
		if(role == 1)
			return "students/viewAdmin";
		if(role == 2)
			return "students/viewStaff";
		if(role == 3)
			return "students/viewStudent";
		return login(model);
	}

}
