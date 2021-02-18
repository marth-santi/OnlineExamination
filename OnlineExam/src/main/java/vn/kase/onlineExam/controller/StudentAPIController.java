package vn.kase.onlineExam.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import vn.kase.onlineExam.model.Mark;
import vn.kase.onlineExam.model.Question;
import vn.kase.onlineExam.model.QuestionResponse;
import vn.kase.onlineExam.model.Subject;
import vn.kase.onlineExam.model.User;
import vn.kase.onlineExam.services.ExamService;
import vn.kase.onlineExam.services.QuestionService;
import vn.kase.onlineExam.services.StudentService;
import vn.kase.onlineExam.services.SubjectService;
import vn.kase.onlineExam.viewModel.ExamResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/students")
public class StudentAPIController {
	@Autowired
	SubjectService subjectService;
	@Autowired
  QuestionService questionService;
  @Autowired
  ExamService examService;

  @Autowired
  StudentService studentService;

  @GetMapping("/test")
  public String testAPI() {
    return "test 123";
  }

  @GetMapping("/getExam")
  @ResponseBody
  public ExamResponse getExam(HttpServletRequest request) {
    Subject subject =(Subject) request.getSession().getAttribute("subject");
    List<Question> listQuestion = questionService.findAllBySubjectId(subject.getId());
    List<QuestionResponse> qResponseList = new ArrayList<QuestionResponse>();
    for (Question q : listQuestion) {
      qResponseList.add(new QuestionResponse(q));
    }

    ExamResponse examResponse = new ExamResponse(subject, qResponseList);
    return examResponse;
  }

  @PostMapping(value="/submitExam")
  public Mark submitExam(HttpServletRequest request, @RequestBody List<QuestionResponse> listResponse) {
    User student = (User)request.getSession().getAttribute("student");
    // Get current subject taken
    Subject subject = (Subject) request.getSession().getAttribute("subject");
    ExamResponse examResponse = new ExamResponse(subject, listResponse);
    // Exam service to mark the response
    Mark mark = examService.markResponse(examResponse);
    mark.setStudentId(student.getId());
    mark.setTestDate(new Date(new java.util.Date().getTime()));

    // Student service to save mark
    Boolean result = studentService.saveMark(mark);
      
    return result ? mark : null;
  }
  
}
