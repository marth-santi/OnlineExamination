package vn.kase.onlineExam.services;

import java.util.List;
import java.util.Optional;

import vn.kase.onlineExam.model.Subject;

public interface SubjectService {

	void deleteAll();

	void deleteAll(List<Subject> entities);

	void delete(Subject entity);

	void deleteById(Integer id);

	long count();

	List<Subject> findAllById(List<Integer> ids);

	List<Subject> findAll();

	boolean existsById(Integer id);

	Optional<Subject> findById(Integer id);

	List<Subject> saveAll(List<Subject> entities);

	Subject save(Subject entity);


}
