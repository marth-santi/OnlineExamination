package vn.kase.onlineExam.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import vn.kase.onlineExam.viewModel.ExamVM;
import vn.kase.onlineExam.viewModel.QuestionVM;

@RestController
@RequestMapping("/editor")
public class EditorHandlingController {

	@PostMapping("/question/add")
	public ModelAndView addQuestion(@Valid @ModelAttribute("exam") ExamVM exam, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ModelAndView("exam/editor", "exam", exam);
		}
		
		exam.getQuestions().add(
				(new QuestionVM()).setIsMultiple(true)
			);
		return new ModelAndView("exam/editor", "exam", exam);
	}
}
