package vn.kase.onlineExam.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "tb_questions")
public class Question implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "question")
    private String question;

    @Column(name = "op1")
    private String op1;

    @Column(name = "op2")
    private String op2;

    @Column(name = "op3")
    private String op3;

    @Column(name = "op4")
    private String op4;

    @Column(name = "answer")
    private String answer;
    
    @Column(name = "subject_id")
    private Integer subjectId;

	public Question() {
	}

	public Question(Integer id, String question, String op1, String op2, String op3, String op4, String answer,
			Integer subjectId) {
		this.id = id;
		this.question = question;
		this.op1 = op1;
		this.op2 = op2;
		this.op3 = op3;
		this.op4 = op4;
		this.answer = answer;
		this.subjectId = subjectId;
	}
	
    
}



