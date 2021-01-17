package vn.kase.onlineExam.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import vn.kase.onlineExam.model.Subject;

@Repository
public interface SubjectRepository extends CrudRepository<Subject, Integer> {

}
