package vn.kase.onlineExam.services;

import java.util.List;
import java.util.Optional;

import vn.kase.onlineExam.model.Question;
import vn.kase.onlineExam.model.Subject;

public interface QuestionService {

	void deleteAll();

	void deleteAll(List<Question> entities);

	void delete(Question entity);

	void deleteById(Integer id);

	long count();

	List<Question> findAllById(List<Integer> ids);

	List<Question> findAll();

	boolean existsById(Integer id);

	Optional<Question> findById(Integer id);

	List<Question> saveAll(List<Question> entities);

	Question save(Question entity);

	Integer deleteBySubjectId(Integer id);

	List<Question> findAllSubjectId(Integer id);

	List<Subject> findAllSubjects();

	List<Question> findAllBySubjectId(Integer subjectId);

}
