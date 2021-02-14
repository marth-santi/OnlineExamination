package vn.kase.onlineExam.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.kase.onlineExam.model.Subject;
import vn.kase.onlineExam.repository.SubjectRepository;

@Service
public class SubjectServiceImpl implements SubjectService{
	@Autowired
	private SubjectRepository subjectRepository;

	@Override
	public Subject save(Subject entity) {
		return subjectRepository.save(entity);
	}

	@Override
	public List<Subject> saveAll(List<Subject> entities) {
		return (List<Subject>)subjectRepository.saveAll(entities);
	}

	@Override
	public Optional<Subject> findById(Integer id) {
		return subjectRepository.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		return subjectRepository.existsById(id);
	}

	@Override
	public List<Subject> findAll() {
		return (List<Subject>)subjectRepository.findAll();
	}

	@Override
	public List<Subject> findAllById(List<Integer> ids) {
		return (List<Subject>)subjectRepository.findAllById(ids);
	}

	@Override
	public long count() {
		return subjectRepository.count();
	}

	@Override
	public void deleteById(Integer id) {
		subjectRepository.deleteById(id);
	}

	@Override
	public void delete(Subject entity) {
		subjectRepository.delete(entity);
	}

	@Override
	public void deleteAll(List<Subject> entities) {
		subjectRepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		subjectRepository.deleteAll();
	}

}
