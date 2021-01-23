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
  private Boolean isCheckedOp1;
  private Boolean isCheckedOp2;
  private Boolean isCheckedOp3;
  private Boolean isCheckedOp4;
  private Boolean isMultiple;

  public Question toQuestion() {
    String answer = "";
    if (this.isCheckedOp1) answer += '1';
    if (this.isCheckedOp2) answer += '2';
    if (this.isCheckedOp3) answer += '3';
    if (this.isCheckedOp4) answer += '4';

    this.answer = answer;
    return (Question)this;
  }
}
