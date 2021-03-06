package vn.kase.onlineExam.controller;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import vn.kase.onlineExam.viewModel.EditorVM;
import vn.kase.onlineExam.viewModel.QuestionVM;

@RestController
@RequestMapping("/editor")
public class EditorHandlingController {

	@PostMapping("/question/add")
	public ModelAndView addQuestion(@Valid @ModelAttribute("exam") EditorVM editorVM, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ModelAndView("exam/editor", "exam", editorVM);
		}

		editorVM.getQuestions().add(
				(new QuestionVM())
				.setIsMultiple(editorVM.getIsMultipleChoiceNewQuestion())
			);
		return new ModelAndView("exam/editor", "exam", editorVM);
	}
	
	@PostMapping("/question/delete")
	public ModelAndView deleteQuestion(@Valid @ModelAttribute("exam") EditorVM editorVM, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ModelAndView("exam/editor", "exam", editorVM);
		}

		Integer deleteId = editorVM.getDeleteQuestionId();
		editorVM.getQuestions().remove(editorVM.getQuestions().get(deleteId));
		System.out.println(editorVM.getQuestions().toString());
		return new ModelAndView("exam/editor", "exam", editorVM);
	}
}
