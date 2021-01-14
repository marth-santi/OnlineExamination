package vn.kase.onlineExam.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import vn.kase.onlineExam.model.Question;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Integer>{

	List<Question> findAllBySubjectId(Integer id);

}
