package vn.kase.onlineExam.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_marks")
public class Mark implements Serializable {
  /**
	 *
	 */
	private static final long serialVersionUID = 1L;
  
  @Id
  @Column(name="student_id")
  private Integer studentId;

  @Id
  @Column(name="subject_id")
  private Integer subjectId;
  
  @Column(name="marks")
  private Integer marks;

  @Column(name="test_date")
  private Date testDate;
}
