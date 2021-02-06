package vn.kase.onlineExam.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.kase.onlineExam.model.Answer;
import vn.kase.onlineExam.model.Question;
import vn.kase.onlineExam.services.AnswerService;
import vn.kase.onlineExam.services.QuestionService;

/*@Controller
@RequestMapping("/students")*/
public class AnswerController {
	/*
	 * @Autowired AnswerService answerService;
	 * 
	 * @Autowired QuestionService questionService;
	 * 
	 * @GetMapping("/doExam/page") private String addAnswer(ModelMap model) {
	 * model.addAttribute("answer", new Answer()); return "students/doExam"; }
	 * 
	 * @PostMapping("/doExam/page") private String addDbAnswer(ModelMap model,
	 * Answer answer) { Optional<Question> op =
	 * questionService.findById(answer.getQuestionId()); Question question =
	 * op.get(); if(answer.getContentAnswer().equals(question.getAnswer())) {
	 * answer.setMarks(1.f); } answerService.save(answer); return "students/doExam";
	 * }
	 */
}
