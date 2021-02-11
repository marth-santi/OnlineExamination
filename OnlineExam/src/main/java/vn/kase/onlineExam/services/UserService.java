package vn.kase.onlineExam.services;

import java.util.List;
import java.util.Optional;

import vn.kase.onlineExam.model.User;

public interface UserService {

	void deleteAll();

	void deleteAll(List<User> entities);

	void delete(User entity);

	void deleteById(Integer id);

	long count();

	List<User> findAllById(List<Integer> ids);

	List<User> findAll();

	boolean existsById(Integer id);

	Optional<User> findById(Integer id);

	List<User> saveAll(List<User> entities);

	User save(User entity);

	User findByUsername(String username);

	List<User> findByRoles(int roles);

	User findByEmail(String email);

}
