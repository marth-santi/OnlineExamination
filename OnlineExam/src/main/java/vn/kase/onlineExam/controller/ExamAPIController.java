package vn.kase.onlineExam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import vn.kase.onlineExam.services.ExamService;
import vn.kase.onlineExam.viewModel.Exam;

@RestController
public class ExamAPIController {
	@Autowired
	private ExamService examService;
	
	@GetMapping("api/exam/{subjectId}")
	@ResponseBody
	public Exam getExam(@PathVariable Integer subjectId) {		
		return examService.getExam(subjectId);
	}
	
	@PostMapping("api/exam")
	@ResponseBody
	public Exam saveExam(@RequestBody Exam examPlan) {		
		return examService.saveExam(examPlan);
	}
	
	@DeleteMapping("api/exam/{subjectId}")
	@ResponseBody
	@Transactional
	public Boolean deleteExam(@PathVariable Integer subjectId) {
		return examService.deleteExam(subjectId);
	}
}
