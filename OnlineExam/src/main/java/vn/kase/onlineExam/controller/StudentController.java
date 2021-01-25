package vn.kase.onlineExam.controller;


import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

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

import vn.kase.onlineExam.model.Question;
import vn.kase.onlineExam.model.Subject;
import vn.kase.onlineExam.services.QuestionService;
import vn.kase.onlineExam.services.SubjectService;



@Controller
@RequestMapping("/students")
public class StudentController {
	@Autowired
	SubjectService subjectService;
	@Autowired
	QuestionService questionService;
	
	@RequestMapping("/viewExam")
	public String viewExam(ModelMap model,@RequestParam(value = "subjectId", required = true) Integer subjectId){
		Optional<Subject> subject = subjectService.findById(subjectId);
		if(subject.isPresent())
		{
			model.addAttribute("subject", subject);
			return "students/subjectWillDo";
		}
		return "students/viewStudent";
	}
	
	@GetMapping("/doExam")
	public String doExam(Model model,Subject subject,HttpServletRequest request
			,RedirectAttributes redirect) {	
		model.addAttribute("subject", subject);
		request.getSession().setAttribute("subject", subject);
		request.getSession().setAttribute("listQuestion", null);
		return "redirect:/students/doExam/page/1";
	}
	
	@GetMapping("/doExam/page/{pageNumber}")
	public String showQuestionPage(HttpServletRequest request, 
			@PathVariable int pageNumber, Model model) {
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("listQuestion");
		int pagesize = 1;
		Subject subject =(Subject) request.getSession().getAttribute("subject");
		List<Question> list = questionService.findAllBySubjectId(subject.getId());
		System.out.println(list.size());
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
		String baseUrl = "/students/doExam/page/";
		
		model.addAttribute("subject", subject);
		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("baseUrl", baseUrl);
		model.addAttribute("questions", pages);

		return "students/doExam";
	}
}
