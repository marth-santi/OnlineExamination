package vn.kase.onlineExam.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.kase.onlineExam.model.Mark;
import vn.kase.onlineExam.model.MarkId;

@Service
public class MarkServiceImpl implements MarkService {
	@Autowired
	MarkRepository markRepository;

	@Override
	public Long findAmountOfFriends(Long userId) {
		return markRepository.findAmountOfFriends(userId);
	}

	@Override
	public List<Mark> findAllByStudentId(Integer studentId) {
		return markRepository.findAllByStudentId(studentId);
	}

	@Override
	public Mark save(Mark entity) {
		return markRepository.save(entity);
	}

	@Override
	public List<Mark> saveAll(List<Mark> entities) {
		return (List<Mark>)markRepository.saveAll(entities);
	}

	@Override
	public Optional<Mark> findById(MarkId id) {
		return markRepository.findById(id);
	}

	@Override
	public boolean existsById(MarkId id) {
		return markRepository.existsById(id);
	}

	@Override
	public List<Mark> findAll() {
		return (List<Mark>)markRepository.findAll();
	}

	@Override
	public List<Mark> findAllById(List<MarkId> ids) {
		return (List<Mark>)markRepository.findAllById(ids);
	}

	@Override
	public long count() {
		return markRepository.count();
	}

	@Override
	public void deleteById(MarkId id) {
		markRepository.deleteById(id);
	}

	@Override
	public void delete(Mark entity) {
		markRepository.delete(entity);
	}

	@Override
	public void deleteAll(List<Mark> entities) {
		markRepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		markRepository.deleteAll();
	}
	
}
