package vn.kase.onlineExam.viewModel;

import java.util.List;

import lombok.Data;
import vn.kase.onlineExam.model.Question;
import vn.kase.onlineExam.model.Subject;

@Data
public class Exam {
	private Subject subject;
	private List<Question> questions;
}
