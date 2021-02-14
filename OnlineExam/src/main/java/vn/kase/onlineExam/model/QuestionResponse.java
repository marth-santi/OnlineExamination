package vn.kase.onlineExam.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
public class QuestionResponse extends Question{
  /**
	 *
	 */
	private static final long serialVersionUID = 1L;
  private Boolean isCheckedOp1 = false;
  private Boolean isCheckedOp2 = false;
  private Boolean isCheckedOp3 = false;
  private Boolean isCheckedOp4 = false;
  private Boolean isMultiple = false;

  public QuestionResponse(Question q) {
    super(q.getId(), q.getQuestion(), q.getOp1(), q.getOp2(), q.getOp3(), q.getOp4(), q.getAnswer(), q.getSubjectId());
    if (this.answer.length() > 1)
      this.isMultiple = true;

    this.setAnswer("");   
  
  }
  
}
