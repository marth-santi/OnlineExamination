package vn.kase.onlineExam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import vn.kase.onlineExam.model.Question;
import vn.kase.onlineExam.model.Subject;
import vn.kase.onlineExam.repository.QuestionRepository;
import vn.kase.onlineExam.repository.SubjectRepository;
import vn.kase.onlineExam.viewModel.ExamEditorVM;

@RestController
public class ExamController {
	
	@Autowired
	private QuestionRepository questionRepo;
	@Autowired
	private SubjectRepository subjectRepo;

	// === VIEW RETURN SECTION ===
	
	@GetMapping("/exam/editor")
	public ModelAndView createExam() {
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("exam/editor");
		return modelView;
	}
	
	// === DATA RETURN SECTION ===
	
	@GetMapping("api/exam/{subjectId}")
	@ResponseBody
	public ExamEditorVM getExam(@PathVariable Integer subjectId) {
		ExamEditorVM examVM = new ExamEditorVM();
		Subject subject = subjectRepo.findById(subjectId).orElse(null);
		if (subject == null)
			return null;
		
		examVM.setSubject(subject);
		
		List<Question> questions = questionRepo.findAllBySubjectId(subject.getId());		
		examVM.setQuestions(questions);
		
		return examVM;
	}
	
	@PostMapping("api/exam")
	@ResponseBody
	public ExamEditorVM saveExam(@RequestBody ExamEditorVM examPlan) {
		Subject savedSubject = subjectRepo.save(examPlan.getSubject());
		for (Question question : examPlan.getQuestions()) {
			question.setSubjectId(savedSubject.getId());
		}
		List<Question> savedQuestions = (List<Question>) questionRepo.saveAll(examPlan.getQuestions());
		
		ExamEditorVM savedExam = new ExamEditorVM();
		savedExam.setQuestions(savedQuestions);
		savedExam.setSubject(savedSubject);
		
		return savedExam;
	}
	
	@DeleteMapping("api/exam/{subjectId}")
	@ResponseBody
	@Transactional
	public Boolean deleteExam(@PathVariable Integer subjectId) {
		Integer res = questionRepo.deleteBySubjectId(subjectId);
		subjectRepo.deleteById(subjectId);
		return res == 0 ? false : true;
	}
}

