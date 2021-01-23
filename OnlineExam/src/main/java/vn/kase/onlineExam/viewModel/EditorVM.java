package vn.kase.onlineExam.viewModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.kase.onlineExam.model.Question;
import vn.kase.onlineExam.model.Subject;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditorVM implements Serializable{
  /**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Subject subject;
	private List<QuestionVM> questions = new ArrayList<QuestionVM>();
	private Boolean isMultipleChoiceNewQuestion;

	public Exam toExam() {
		Exam exam = new Exam().setSubject(this.subject);
		List<Question> examQuestions = new ArrayList<Question>();
		for (QuestionVM q : this.questions) {
			examQuestions.add(q.toQuestion());
		}
		exam.setQuestions(examQuestions);
		return exam;
	}
	
	public EditorVM(Exam exam){
		this.setSubject(exam.getSubject());
			
		for (Question q : exam.getQuestions()) {
			this.questions.add(new QuestionVM(q));
		}
	}
}
