package vn.kase.onlineExam.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class EditorController {

	@GetMapping("/question/add")
	public ModelAndView addQuestion() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("exam/editorFragment :: questionMultiple");
		return mv;
	}
}
