package vn.kase.onlineExam.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@IdClass(MarkId.class)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Accessors(chain=true)
@Entity
@Table(name = "tb_marks")
public class Mark implements Serializable {
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
  @DateTimeFormat(pattern = "MM/dd/yyyy")
  private Date testDate;
  
  private Subject subject;
}
