package vn.kase.onlineExam.viewModel;

import java.io.Serializable;
import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.kase.onlineExam.model.QuestionResponse;
import vn.kase.onlineExam.model.Subject;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoExamVM implements Serializable{
  /**
	 *
	 */
	private static final long serialVersionUID = 1L;
  private Subject subject;
  private List<QuestionResponse> questionResponseList;
}
