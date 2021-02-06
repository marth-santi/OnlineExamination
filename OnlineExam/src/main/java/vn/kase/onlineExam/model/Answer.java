package vn.kase.onlineExam.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_answer")
public class Answer {
	@Id
    @Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;
	
	@Column(name = "content_answer")
	protected String contentAnswer;
	
	@Column(name = "marks")
	protected float marks;
	
	@Column(name = "question_id")
	protected int questionId;
	
	@Column(name = "student_id")
	protected int studentId;
	
}
