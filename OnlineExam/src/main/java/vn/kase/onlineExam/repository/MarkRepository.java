package vn.kase.onlineExam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.kase.onlineExam.model.Mark;
import vn.kase.onlineExam.model.MarkId;

@Repository
public interface MarkRepository extends CrudRepository<Mark, MarkId> {

	@Query(value = "some query", nativeQuery = true)
    Long findAmountOfFriends(@Param("userId") Long userId);
	List<Mark> findAllByStudentId(Integer studentId);
}
