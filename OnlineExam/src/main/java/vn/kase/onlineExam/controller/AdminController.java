package vn.kase.onlineExam.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vn.kase.onlineExam.model.User;
import vn.kase.onlineExam.services.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	UserService userService;
int valueFind=0;
@RequestMapping("/managerUsers")
	public String managerUsers(ModelMap model, @RequestParam(defaultValue = "1") int value) {
	model.addAttribute("listUsers", userService.findByRoles(value));
	valueFind=value;
		return "admin/managerUser";
	}

@GetMapping("/add")
	public String add(ModelMap model) {
		model.addAttribute("user", new User());
			return "admin/createOrEdit";
	}
@PostMapping("/saveOrUpdate")
	public String save(ModelMap model, User user) {
		if (user.getId() == null) {
			String message1;
			User checkUser = userService.findByEmail(user.getEmail());
			if (checkUser != null) {
				message1 = "*Email already exist!";
				model.addAttribute("message1", message1);
				return "admin/createOrEdit";
			}
			checkUser = userService.findByUsername(user.getUsername());
			if (checkUser != null) {
				message1 = "*Username already exist!";
				model.addAttribute("message1", message1);
				return "admin/createOrEdit";
			}
		}
		String message = "New user inserted!";
		if (user.getId() != null && user.getId() > 0) {
			message = "User Updated!";
		}
		MD5 md5 = new MD5();
		user.setPass(md5.getMd5(user.getPass()));
		userService.save(user);
		model.addAttribute(user);
		model.addAttribute("message", message);
		return "admin/createOrEdit";
	}

@GetMapping("/edit/{id}")
public String edit(ModelMap model, @PathVariable(name="id") Integer id) {
	Optional<User> optUser = userService.findById(id);
	if(optUser.isPresent()) {
		model.addAttribute("user", optUser.get());
	}
	else
	{
		return managerUsers(model,0);
	}
	return "admin/createOrEdit";
}

@GetMapping("/delete/{id}")
public String delete(ModelMap model,@PathVariable(name="id") Integer id) {
	String message;
	try {
		userService.deleteById(id);
	} catch (Exception e) {
		message = "This student was assigned!";
		model.addAttribute("message",message);
	}
	
	return managerUsers(model,valueFind);
}

}
