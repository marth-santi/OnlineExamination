package vn.kase.onlineExam.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import vn.kase.onlineExam.viewModel.ExamEditorVM;

@RestController
public class ExamController {

	// === VIEW RETURN SECTION ===
	
	@GetMapping("/exam/editor")
	public ModelAndView createExam() {
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("exam/editor");
		return modelView;
	}
	
	// === DATA RETURN SECTION ===
	
	@GetMapping("/exam/{subjectId}")
	@ResponseBody
	public ExamEditorVM getExam(@PathVariable Integer subjectId) {
		return null;
	}
	
	@PostMapping("/exam")
	@ResponseBody
	public ExamEditorVM saveExam(@RequestBody ExamEditorVM examPlan) {
		return examPlan;
	}
}
