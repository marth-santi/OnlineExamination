package vn.kase.onlineExam.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.kase.onlineExam.model.Answer;
import vn.kase.onlineExam.repository.AnswerRepository;
@Service
public class AnswerServiceImpl implements AnswerService{
	@Autowired
	AnswerRepository answerRepository;

	@Override
	public Answer save(Answer entity) {
		return answerRepository.save(entity);
	}

	@Override
	public List<Answer> saveAll(List<Answer> entities) {
		return (List<Answer>) answerRepository.saveAll(entities);
	}

	@Override
	public Optional<Answer> findById(Integer id) {
		return answerRepository.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		return answerRepository.existsById(id);
	}

	@Override
	public List<Answer> findAll() {
		return (List<Answer>)answerRepository.findAll();
	}

	@Override
	public List<Answer> findAllById(List<Integer> ids) {
		return (List<Answer>)answerRepository.findAllById(ids);
	}

	@Override
	public long count() {
		return answerRepository.count();
	}

	@Override
	public void deleteById(Integer id) {
		answerRepository.deleteById(id);
	}

	@Override
	public void delete(Answer entity) {
		answerRepository.delete(entity);
	}

	@Override
	public void deleteAll(List<Answer> entities) {
		answerRepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		answerRepository.deleteAll();
	}
	

}
