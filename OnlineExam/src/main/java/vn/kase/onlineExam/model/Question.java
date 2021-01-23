package vn.kase.onlineExam.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
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
    protected Integer id;

    @Column(name = "question")
    protected String question;

    @Column(name = "op1")
    protected String op1;

    @Column(name = "op2")
    protected String op2;

    @Column(name = "op3")
    protected String op3;

    @Column(name = "op4")
    protected String op4;

    @Column(name = "answer")
    protected String answer;
    
    @Column(name = "subject_id")
    protected Integer subjectId;
}



