package vn.kase.onlineExam.viewModel;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import vn.kase.onlineExam.model.Question;
import vn.kase.onlineExam.model.Subject;

@Data
public class Exam implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Subject subject;
	private List<Question> questions;
}
