package vn.kase.onlineExam.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.kase.onlineExam.model.Question;
import vn.kase.onlineExam.model.Subject;
import vn.kase.onlineExam.repository.QuestionRepository;
import vn.kase.onlineExam.repository.SubjectRepository;

@Service
public class QuestionServiceImpl implements QuestionService {
	@Autowired
	QuestionRepository questionRepository;
	@Autowired
	SubjectRepository subjectRepository;
	
	@Override
	public List<Subject> findAllSubjects(){
		return (List<Subject>)subjectRepository.findAll();
	}
	

	@Override
	public List<Question> findAllBySubjectId(Integer subjectId) {
		return questionRepository.findAllBySubjectId(subjectId);
	}


	@Override
	public Integer deleteBySubjectId(Integer id) {
		return questionRepository.deleteBySubjectId(id);
	}

	@Override
	public Question save(Question entity) {
		return questionRepository.save(entity);
	}

	@Override
	public List<Question> saveAll(List<Question> entities) {
		return (List<Question>)questionRepository.saveAll(entities);
	}

	@Override
	public Optional<Question> findById(Integer id) {
		return questionRepository.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		return questionRepository.existsById(id);
	}

	@Override
	public List<Question> findAll() {
		return (List<Question>)questionRepository.findAll();
	}

	@Override
	public List<Question> findAllById(List<Integer> ids) {
		return (List<Question>)questionRepository.findAllById(ids);
	}

	@Override
	public long count() {
		return questionRepository.count();
	}

	@Override
	public void deleteById(Integer id) {
		questionRepository.deleteById(id);
	}

	@Override
	public void delete(Question entity) {
		questionRepository.delete(entity);
	}

	@Override
	public void deleteAll(List<Question> entities) {
		questionRepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		questionRepository.deleteAll();
	}


	@Override
	public List<Question> findAllSubjectId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}	

}
