package vn.kase.onlineExam.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import vn.kase.onlineExam.model.Question;

@Repository
public interface QuestionRepository extends PagingAndSortingRepository<Question, Integer>{

	List<Question> findAllBySubjectId(Integer id);
	Integer deleteBySubjectId(Integer id);
}
