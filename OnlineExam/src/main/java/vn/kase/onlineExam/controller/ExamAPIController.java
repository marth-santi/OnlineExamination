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

import vn.kase.onlineExam.model.Question;
import vn.kase.onlineExam.model.Subject;
import vn.kase.onlineExam.repository.QuestionRepository;
import vn.kase.onlineExam.repository.SubjectRepository;
import vn.kase.onlineExam.viewModel.Exam;

@RestController
public class ExamAPIController {
	@Autowired
	private QuestionRepository questionRepo;
	@Autowired
	private SubjectRepository subjectRepo;
	
	@GetMapping("api/exam/{subjectId}")
	@ResponseBody
	public Exam getExam(@PathVariable Integer subjectId) {
		Exam exam = new Exam();
		Subject subject = subjectRepo.findById(subjectId).orElse(null);
		if (subject == null)
			return null;
		
		exam.setSubject(subject);
		
		List<Question> questions = questionRepo.findAllBySubjectId(subject.getId());		
		exam.setQuestions(questions);
		
		return exam;
	}
	
	@PostMapping("api/exam")
	@ResponseBody
	public Exam saveExam(@RequestBody Exam examPlan) {
		Subject savedSubject = subjectRepo.save(examPlan.getSubject());
		for (Question question : examPlan.getQuestions()) {
			question.setSubjectId(savedSubject.getId());
		}
		List<Question> savedQuestions = (List<Question>) questionRepo.saveAll(examPlan.getQuestions());
		
		Exam savedExam = new Exam();
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