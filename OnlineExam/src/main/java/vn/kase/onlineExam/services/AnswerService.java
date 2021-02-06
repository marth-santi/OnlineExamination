package vn.kase.onlineExam.services;

import java.util.List;
import java.util.Optional;

import vn.kase.onlineExam.model.Answer;

public interface AnswerService {

	void deleteAll();

	void deleteAll(List<Answer> entities);

	void delete(Answer entity);

	void deleteById(Integer id);

	long count();

	List<Answer> findAllById(List<Integer> ids);

	List<Answer> findAll();

	boolean existsById(Integer id);

	Optional<Answer> findById(Integer id);

	List<Answer> saveAll(List<Answer> entities);

	Answer save(Answer entity);

}
