package vn.kase.onlineExam.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import vn.kase.onlineExam.services.ExamService;
import vn.kase.onlineExam.viewModel.EditorVM;
import vn.kase.onlineExam.viewModel.Exam;

@RestController
public class ExamController {
	
	@Autowired
	private ExamService examService;
	
	@GetMapping("/exam/editor")
	public ModelAndView createExam() {
		return new ModelAndView("exam/editor", "exam", new EditorVM());
	}

	@GetMapping("/exam/editor/{subjectId}")
	public ModelAndView editExam(@PathVariable(name = "subjectId") Integer subjectId) {
		Exam exam = examService.getExam(subjectId);
		EditorVM editorVM = new EditorVM(exam);
		return new ModelAndView("exam/editor", "exam", editorVM);
	}
	
	@PostMapping("/exam/submitExam")
	public ModelAndView submitExam(@Valid @ModelAttribute(value = "exam") EditorVM editorVM, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ModelAndView("exam/editor", "exam", editorVM);
		}
		
		Exam savedExam = examService.saveExam(editorVM.toExam());
		System.out.println(new EditorVM(savedExam));
		return new ModelAndView("exam/editor", "exam", new EditorVM(savedExam));
	}
	
		
}

