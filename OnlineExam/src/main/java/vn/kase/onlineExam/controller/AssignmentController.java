package vn.kase.onlineExam.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import vn.kase.onlineExam.model.Mark;
import vn.kase.onlineExam.model.Subject;
import vn.kase.onlineExam.model.User;
import vn.kase.onlineExam.repository.MarkRepository;
import vn.kase.onlineExam.repository.SubjectRepository;
import vn.kase.onlineExam.repository.UserRepository;
import vn.kase.onlineExam.services.StudentService;
import vn.kase.onlineExam.viewModel.AssignmentVM;
import vn.kase.onlineExam.viewModel.StudentAssignmentStatus;

@RestController
@RequestMapping("/assignment")
public class AssignmentController {

	@Autowired
  private SubjectRepository subjectRepo;
	@Autowired
  private UserRepository userRepo;
  @Autowired
  private MarkRepository markRepo;

  @Autowired
  private StudentService studentService;
  
  @GetMapping("/listexam")
  public ModelAndView startAssignment() {
    List<Subject> subjects = (List<Subject>) subjectRepo.findAll();
    return new ModelAndView("assignment/listSubject", "subjects", subjects);
  }
  
  @GetMapping("/subject/{subjectId}")
  public ModelAndView assignToStudents(@PathVariable(name="subjectId") Integer subjectId) {
    Subject subject = subjectRepo.findById(subjectId).orElse(null);
    List<User> students = studentService.findAllStudent();
    // TODO add filter to limit students display (based on user options on UI)
    List<StudentAssignmentStatus> listStudentStatus = new ArrayList<StudentAssignmentStatus>();
    for (User student : students) {
      StudentAssignmentStatus studentStatus = studentService.getStudentAssignmentStatusForSubject(student, subject);
      listStudentStatus.add(studentStatus);
    }

    AssignmentVM vm = new AssignmentVM(subject, listStudentStatus);
    return new ModelAndView("assignment/assignStudent", "assignVM", vm);
  }
  
  @PostMapping("/assign")
  public RedirectView assign(@ModelAttribute(value = "assignVM") AssignmentVM assignVM, BindingResult bindingResult) {
    Subject subject = subjectRepo.findById(assignVM.getSubject().getId()).orElse(null);
    Boolean result = studentService.assignSubjectToStudents(subject, assignVM.getStudents());
    return new RedirectView("/assignment/subject/" + subject.getId());
  }
}
