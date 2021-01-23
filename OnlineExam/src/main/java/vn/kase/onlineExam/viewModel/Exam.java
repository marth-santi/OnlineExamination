package vn.kase.onlineExam.viewModel;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;
import vn.kase.onlineExam.model.Question;
import vn.kase.onlineExam.model.Subject;

@Data
@Accessors(chain = true)
public class Exam implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Subject subject;
	private List<Question> questions;
}
