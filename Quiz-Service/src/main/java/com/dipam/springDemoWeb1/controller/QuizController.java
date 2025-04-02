package com.dipam.springDemoWeb1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dipam.springDemoWeb1.entity.QuestionWrapper;
import com.dipam.springDemoWeb1.entity.QuizDto;
import com.dipam.springDemoWeb1.entity.Response;
import com.dipam.springDemoWeb1.service.QuizService;

@RestController
@RequestMapping("quiz")
public class QuizController {
	@Autowired
	QuizService qs;
	@PostMapping("create")
	public String createQuiz(@RequestBody QuizDto dto){
		qs.createQuiz(dto.getCategory(),dto.getNoQ(),dto.getTitle());
		return "success";
	}
	@GetMapping("get/{id}")
	public List<QuestionWrapper> getQuizQuestions(@PathVariable int id){
		return qs.getQuestions(id);
	}
	
	@PostMapping("submit/{id}")
	public int submitQuiz(@PathVariable int id,@RequestBody List<Response> res) {
		return qs.submitQuiz(id,res);
	}
}
