package vn.kase.onlineExam.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper=false)
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
  
  public Boolean checkAnswer(String correctAnswer) {
    char[] studentChoices = getStudentResponse();
    // If no answer => false
    if (studentChoices.length == 0)
      return false;

    char[] correctChoices = correctAnswer.toCharArray();
    // Choice less or more than correct
    if (correctChoices.length != studentChoices.length)
      return false;
    // Student choices number equals Correct choices => check each choice
    for (char s : studentChoices) {
      if (!correctAnswer.contains(String.valueOf(s)))
        return false;
    }
    return true;
  }
  
  private char[] getStudentResponse() {
    String response = "";
    if (isCheckedOp1)
      response = response + "1";
    if (isCheckedOp2)
      response = response + "2";
    if (isCheckedOp3)
      response = response + "3";
    if (isCheckedOp4)
      response = response + "4";

    return response.toCharArray();
  }
  
}
