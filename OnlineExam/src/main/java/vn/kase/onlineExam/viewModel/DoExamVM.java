package vn.kase.onlineExam.viewModel;

import org.springframework.beans.support.PagedListHolder;

import vn.kase.onlineExam.model.Subject;

public class DoExamVM {
  private Subject subject;
  private PagedListHolder<?> questionPageList;
}
