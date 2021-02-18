package vn.kase.onlineExam.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.kase.onlineExam.model.Mark;
import vn.kase.onlineExam.model.Subject;
import vn.kase.onlineExam.model.User;
import vn.kase.onlineExam.repository.MarkRepository;
import vn.kase.onlineExam.repository.UserRepository;
import vn.kase.onlineExam.viewModel.StudentAssignmentStatus;

@Service
public class StudentService {
  

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private MarkRepository markRepo;

  public List<Mark> findStudentMarkForSubject(User student, Subject subject) {
    return markRepo.findByStudentIdAndSubjectId(student.getId(), subject.getId());
  }

  public StudentAssignmentStatus getStudentAssignmentStatusForSubject(User student, Subject subject) {
    StudentAssignmentStatus studentStatus = new StudentAssignmentStatus(student, subject);

    List<Mark> marks = findStudentMarkForSubject(student, subject);
    // TODO: add logic to select / filter mark
    if (marks != null && !marks.isEmpty()) {
      studentStatus.setWasAssigned(true);
      studentStatus.setIsAssigned(true);
      studentStatus.setIsExamDone(marks.get(0).getTestDate() != null);
    }
    return studentStatus;
  }

  public List<User> findAllStudent() {
    // TODO filter student user by roles
    return (List<User>) userRepo.findAll();
  }
  
  @Transactional
  public Boolean assignSubjectToStudents(Subject subject, List<StudentAssignmentStatus> listStudentStatus) {
    List<Mark> marksToSave = new ArrayList<Mark>();
    List<Mark> marksToDelete = new ArrayList<Mark>();
    Mark newMark, deleteMark;
    // NOTE: the list Student Status only have student Id
    try{
      for (StudentAssignmentStatus status : listStudentStatus) {
        // If Assignment status not change => do Nothing
        if (status.getIsAssigned() == status.getWasAssigned())
          continue;
  
        // If new Assignment (ASSIGN False => True)
        if (status.getIsAssigned()) {
          newMark = new Mark();
          newMark.setStudentId(status.getId());
          newMark.setSubjectId(subject.getId());
          marksToSave.add(newMark);
        }
        // If user want cancel old Assignment (ASSIGN True => False) AND exam not Taken yet => revert assignment
        if (!status.getIsAssigned() && !status.getIsExamDone()) {
          deleteMark = new Mark();
          deleteMark.setStudentId(status.getId());
          deleteMark.setSubjectId(subject.getId());
          marksToDelete.add(deleteMark);
        }
      }
  
      if (!marksToDelete.isEmpty())
        markRepo.deleteAll(marksToDelete);
      
      if (!marksToSave.isEmpty())
        markRepo.saveAll(marksToSave);
      return true;
    }
    catch (Exception e) {
      System.out.println(e.getStackTrace());
      return false;
    }
    
  }

  public Boolean saveMark(Mark mark) {
    try {
      markRepo.save(mark);
      return true;
    }
    catch (Exception e) {
      System.out.println(e.getStackTrace());
      return false;
    }
  }
}
