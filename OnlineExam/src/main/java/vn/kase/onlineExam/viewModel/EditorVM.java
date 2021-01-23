package vn.kase.onlineExam.viewModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import vn.kase.onlineExam.model.Question;
import vn.kase.onlineExam.model.Subject;

@Data
public class EditorVM implements Serializable{
  /**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Subject subject;
	private List<QuestionVM> questions;
	private Boolean isMultipleChoiceNewQuestion;

	private Exam toExam() {
		Exam exam = new Exam()
			.setSubject(this.subject);
		exam.setQuestions(new ArrayList<Question>());
		for (QuestionVM q : this.questions) {
			exam.getQuestions().add(q.toQuestion());
		}
		return exam;
	}
}
