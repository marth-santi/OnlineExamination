package vn.kase.onlineExam.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.kase.onlineExam.model.User;
import vn.kase.onlineExam.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;

	@Override
	public User save(User entity) {
		return userRepository.save(entity);
	}

	@Override
	public List<User> saveAll(List<User> entities) {
		return (List<User>) userRepository.saveAll(entities);
	}

	@Override
	public Optional<User> findById(Integer id) {
		return userRepository.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		return userRepository.existsById(id);
	}

	@Override
	public List<User> findAll() {
		return (List<User>)userRepository.findAll();
	}

	@Override
	public List<User> findAllById(List<Integer> ids) {
		return (List<User>)userRepository.findAllById(ids);
	}

	@Override
	public long count() {
		return userRepository.count();
	}

	@Override
	public void deleteById(Integer id) {
		userRepository.deleteById(id);
	}

	@Override
	public void delete(User entity) {
		userRepository.delete(entity);
	}

	@Override
	public void deleteAll(List<User> entities) {
		userRepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		userRepository.deleteAll();
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public List<User> findByRoles(int roles) {
		return userRepository.findByRoles(roles);
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

}
