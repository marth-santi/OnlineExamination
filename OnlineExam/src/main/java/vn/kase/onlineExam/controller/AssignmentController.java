package vn.kase.onlineExam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import vn.kase.onlineExam.model.Subject;
import vn.kase.onlineExam.repository.SubjectRepository;

@RestController
@RequestMapping("/assignment")
public class AssignmentController {

	@Autowired
  private SubjectRepository subjectRepo;
  
  @GetMapping("/listexam")
  public ModelAndView startAssignment() {
    List<Subject> subjects = (List<Subject>)subjectRepo.findAll();
    return new ModelAndView("assignment/listSubject", "subjects", subjects);
  }
  
  @PostMapping("/assign")
  public String assign() {
    return "1";
  }
}
