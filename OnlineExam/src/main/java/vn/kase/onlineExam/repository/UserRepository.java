package vn.kase.onlineExam.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import vn.kase.onlineExam.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{
	User findByUsername(String username);
}
