package vn.kase.onlineExam.viewModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import vn.kase.onlineExam.model.Question;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
public class QuestionVM extends Question{
  /**
	 *
	 */
	private static final long serialVersionUID = 1L;
  private Boolean isCheckedOp1 = false;
  private Boolean isCheckedOp2 = false;
  private Boolean isCheckedOp3 = false;
  private Boolean isCheckedOp4 = false;
  private Boolean isMultiple;

  public Question toQuestion() {
    String answer = "";
    if (this.isCheckedOp1)
      answer += '1';
    if (this.isCheckedOp2)
      answer += '2';
    if (this.isCheckedOp3)
      answer += '3';
    if (this.isCheckedOp4)
      answer += '4';

    this.answer = answer;
    return new Question(id, question, op1, op2, op3, op4, answer, subjectId);
  }
  
  public QuestionVM(Question q) {
    super(q.getId(), q.getQuestion(), q.getOp1(), q.getOp2(), q.getOp3(), q.getOp4(), q.getAnswer(), q.getSubjectId());

    if (answer.contains("1"))
      isCheckedOp1 = true;
    if (answer.contains("2"))
      isCheckedOp2 = true;
    if (answer.contains("3"))
      isCheckedOp3 = true;
    if (answer.contains("4"))
      isCheckedOp4 = true;
  }
}
