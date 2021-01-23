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
    String tempAnswer = "";
    if (this.isCheckedOp1)
      tempAnswer += '1';
    if (this.isCheckedOp2)
      tempAnswer += '2';
    if (this.isCheckedOp3)
      tempAnswer += '3';
    if (this.isCheckedOp4)
      tempAnswer += '4';

    if (this.answer == null || this.answer.isBlank())
      this.answer = tempAnswer;
    return new Question(id, question, op1, op2, op3, op4, answer, subjectId);
  }
  
  public QuestionVM(Question q) {
    super(q.getId(), q.getQuestion(), q.getOp1(), q.getOp2(), q.getOp3(), q.getOp4(), q.getAnswer(), q.getSubjectId());

    if (q.getAnswer().contains("1"))
      this.isCheckedOp1 = true;
    if (q.getAnswer().contains("2"))
      this.isCheckedOp2 = true;
    if (q.getAnswer().contains("3"))
      this.isCheckedOp3 = true;
    if (q.getAnswer().contains("4"))
      this.isCheckedOp4 = true;
  }
}
