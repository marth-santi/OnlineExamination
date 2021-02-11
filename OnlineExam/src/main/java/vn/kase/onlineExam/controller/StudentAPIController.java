package vn.kase.onlineExam.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import vn.kase.onlineExam.model.Question;
import vn.kase.onlineExam.model.QuestionResponse;
import vn.kase.onlineExam.model.Subject;
import vn.kase.onlineExam.services.QuestionService;
import vn.kase.onlineExam.services.SubjectService;

@RestController
@RequestMapping("/api/students")
public class StudentAPIController {
	@Autowired
	SubjectService subjectService;
	@Autowired
	QuestionService questionService;

  @GetMapping("/test")
  public String testAPI() {
    return "test 123";
  }

  @GetMapping("/getExam")
  @ResponseBody
  public Object getExam(HttpServletRequest request) {
    Subject subject =(Subject) request.getSession().getAttribute("subject");
    List<Question> listQuestion = questionService.findAllBySubjectId(subject.getId());
    List<QuestionResponse> qResponseList = new ArrayList<QuestionResponse>();
    for (Question q : listQuestion) {
      qResponseList.add(new QuestionResponse(q));
    }
    return qResponseList;
  }
}
