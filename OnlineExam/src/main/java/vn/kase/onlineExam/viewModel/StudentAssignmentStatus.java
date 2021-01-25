package vn.kase.onlineExam.viewModel;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import vn.kase.onlineExam.model.Mark;
import vn.kase.onlineExam.model.Subject;
import vn.kase.onlineExam.model.User;
import vn.kase.onlineExam.repository.MarkRepository;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
public class StudentAssignmentStatus extends User {
    
  private Integer subjectId;
  private Boolean isAssigned = false;
  private Boolean isExamDone = false;

  // This field to track UI user change on assignment status
  private Boolean wasAssigned = false;

  public StudentAssignmentStatus(User student, Subject subject) {
    super(student.getId(), student.getFullname(), student.getMobile(), student.getEmail(), student.getAddress(), student.getUsername(), student.getPass(),
        student.getRoles(), student.isConfirm());

    this.subjectId = subject.getId();
  }
}
