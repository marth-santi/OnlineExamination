package vn.kase.onlineExam.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/students")
public class StudentAPIController {

  @GetMapping("/test")
  public String testAPI() {
    return "test 123";
  }
}
