package vn.kase.onlineExam.controller;


import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import vn.kase.onlineExam.model.Answer;
import vn.kase.onlineExam.model.Mark;
import vn.kase.onlineExam.model.Question;
import vn.kase.onlineExam.model.Subject;
import vn.kase.onlineExam.model.User;
import vn.kase.onlineExam.services.AnswerService;
import vn.kase.onlineExam.services.MarkService;
import vn.kase.onlineExam.services.QuestionService;
import vn.kase.onlineExam.services.SubjectService;



@Controller
@RequestMapping("/students")
public class StudentController {
	@Autowired
	SubjectService subjectService;
	@Autowired
	QuestionService questionService;
	@Autowired
	AnswerService answerService;
	@Autowired
	MarkService markService;
	
	@RequestMapping("/viewExam")
	public String viewExam(ModelMap model,@RequestParam(value = "subjectId", required = true) int subjectId){
		Optional<Subject> subject = subjectService.findById(subjectId);
		if(subject.isPresent())
		{
			model.addAttribute("subject", subject);
			return "students/subjectWillDo";
		}
		return "students/viewStudent";
	}
	
	@GetMapping("/doExam")
	public String doExam(Model model,Subject subject,HttpServletRequest request, RedirectAttributes redirect) {	
		model.addAttribute("subject", subject);
		request.getSession().setAttribute("subject", subject);
		request.getSession().setAttribute("listQuestion", null);
		return "/doExamApp/index";
	}

	@GetMapping("/doExam/page/{pageNumber}")
	public String showQuestionPage(HttpServletRequest request, @PathVariable int pageNumber, Model model) {
		String baseUrl = "/students/doExam/page/";
		model.addAttribute("answer", new Answer());
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("listQuestion");
		int pagesize = 1;
		Subject subject =(Subject) request.getSession().getAttribute("subject");
		List<Question> list = questionService.findAllBySubjectId(subject.getId());
		if (pages == null) {
			pages = new PagedListHolder<>(list);
			pages.setPageSize(pagesize);
		} else {
			final int goToPage = pageNumber - 1;
			if (goToPage <= pages.getPageCount() && goToPage >= 0) {
				pages.setPage(goToPage);
			}
		}
		request.getSession().setAttribute("listQuestion", pages);
		int current = pages.getPage() + 1;
		int begin = Math.max(1, current - list.size());
		int end = Math.min(begin + 5, pages.getPageCount());
		int totalPageCount = pages.getPageCount();
		
		model.addAttribute("subject", subject);
		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("baseUrl", baseUrl);
		model.addAttribute("questions", pages);
		// System.out.println(answer.getStudentId()+"edf");
		return "students/doExam";
	}
	
	@GetMapping("/results")
	public String result(ModelMap model, HttpSession session) {
		User user =(User) session.getAttribute("student");
		List<Mark> listMarks = markService.findAllByStudentId(user.getId());
		for (Mark mark : listMarks) {
			Subject subject = subjectService.findById(mark.getSubjectId()).get();
			mark.setSubject(subject);
			/*
			 * if(mark.getSubject().getEndDate().compareTo(new Date())<0); {
			 * mark.setMarks(0); markService.save(mark); }
			 */
		}
		model.addAttribute("listMarks", listMarks);
		return "students/results";
	}
}
