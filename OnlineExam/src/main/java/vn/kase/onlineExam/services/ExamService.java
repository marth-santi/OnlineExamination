package vn.kase.onlineExam.services;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.kase.onlineExam.model.Mark;
import vn.kase.onlineExam.model.Question;
import vn.kase.onlineExam.model.QuestionResponse;
import vn.kase.onlineExam.model.Subject;
import vn.kase.onlineExam.repository.QuestionRepository;
import vn.kase.onlineExam.repository.SubjectRepository;
import vn.kase.onlineExam.viewModel.Exam;
import vn.kase.onlineExam.viewModel.ExamResponse;

@Service
public class ExamService {  
	@Autowired
	private QuestionRepository questionRepo;
	@Autowired
  private SubjectRepository subjectRepo;
  
	public Exam getExam(Integer subjectId) {
		Exam exam = new Exam();
		Subject subject = subjectRepo.findById(subjectId).orElse(null);
		if (subject == null)
			return null;
		
		exam.setSubject(subject);
		
		List<Question> questions = questionRepo.findAllBySubjectId(subject.getId());		
		exam.setQuestions(questions);
		
		return exam;
	}
	
	public Exam saveExam(Exam examPlan) {
		Subject savedSubject = subjectRepo.save(examPlan.getSubject());
		for (Question question : examPlan.getQuestions()) {
			question.setSubjectId(savedSubject.getId());
		}
		List<Question> savedQuestions = (List<Question>) questionRepo.saveAll(examPlan.getQuestions());
		
		examPlan.setQuestions(savedQuestions);
		examPlan.setSubject(savedSubject);
		
		return examPlan;
	}
	
	@Transactional
	public Boolean deleteExam(Integer subjectId) {
		Integer res = questionRepo.deleteBySubjectId(subjectId);
		subjectRepo.deleteById(subjectId);
		return res == 0 ? false : true;
	}

	public Mark markResponse(ExamResponse examResponse) {
		List<Question> questions = questionRepo.findAllBySubjectId(examResponse.getSubject().getId());
		Integer correct = 0;
		Integer total = questions.size();
		for (QuestionResponse questionRes : examResponse.getResponses()) {
			Question question = questions.stream().filter(q -> (q.getId() == questionRes.getId())).findFirst().orElse(null);
			if (question == null)
				return null;
			if (questionRes.checkAnswer(question.getAnswer()))
				correct++;
		}
		
		Integer mark = correct * 100 / total;
		return new Mark()
				.setMarks(mark)
				.setSubject(examResponse.getSubject());
	}
}