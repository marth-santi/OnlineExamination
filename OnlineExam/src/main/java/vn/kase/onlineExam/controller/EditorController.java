package vn.kase.onlineExam.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import vn.kase.onlineExam.model.Question;
import vn.kase.onlineExam.viewModel.Exam;

@RestController
@RequestMapping("/editor")
public class EditorController {

	@PostMapping("/question/add")
	public ResponseEntity<String> addQuestion(@RequestBody List<Question> questions) {
		ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        
		resolver.setCacheable(false);
		resolver.setPrefix("templates/");
		resolver.setSuffix(".html");
		resolver.setCharacterEncoding("UTF-8");
		resolver.setTemplateMode("HTML");
		resolver.setOrder(1);
		resolver.setCheckExistence(true);

		TemplateEngine engine = new TemplateEngine();
		Set<ITemplateResolver> templateResolvers = new HashSet<>();
		templateResolvers.add(resolver);
		engine.setTemplateResolvers(templateResolvers);
		
		final Context ctx = new Context();

		Question newQuestion = new Question();
		newQuestion.setQuestion("New Question");
		questions.add(newQuestion);
		Exam exam = new Exam();
		exam.setQuestions(questions);
		ctx.setVariable("questions", exam.getQuestions());
		
		return ResponseEntity.status(HttpStatus.OK)
			.body(engine.process("exam/questionList", new HashSet<String>(Arrays.asList("questionList")), ctx));
	}
}
