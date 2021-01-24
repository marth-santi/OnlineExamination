package vn.kase.onlineExam.repository;

import java.util.List;
import java.util.Optional;

import vn.kase.onlineExam.model.Mark;
import vn.kase.onlineExam.model.MarkId;

public interface MarkService {

	void deleteAll();

	void deleteAll(List<Mark> entities);

	void delete(Mark entity);

	void deleteById(MarkId id);

	long count();

	List<Mark> findAllById(List<MarkId> ids);

	List<Mark> findAll();

	boolean existsById(MarkId id);

	Optional<Mark> findById(MarkId id);

	List<Mark> saveAll(List<Mark> entities);

	Mark save(Mark entity);

	List<Mark> findAllByStudentId(Integer studentId);

	Long findAmountOfFriends(Long userId);

}
