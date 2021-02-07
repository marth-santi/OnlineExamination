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
	String message = "New user inserted!";
	if(user.getId() != null && user.getId() >0) {
		message = "User Updated!";
	}
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
	userService.deleteById(id);
	return managerUsers(model,valueFind);
}

}
