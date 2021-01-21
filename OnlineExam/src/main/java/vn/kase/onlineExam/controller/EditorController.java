package vn.kase.onlineExam.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import vn.kase.onlineExam.model.Question;
import vn.kase.onlineExam.viewModel.Exam;

@RestController
@RequestMapping("/editor")
public class EditorController {

	@PostMapping("/question/add")
	public ModelAndView addQuestion(@ModelAttribute("exam") Exam exam) {
		exam.getQuestions().add(new Question());
		ModelAndView mv = new ModelAndView("exam/editor");
		mv.addObject("exam", exam);
		return mv;
	}
}
