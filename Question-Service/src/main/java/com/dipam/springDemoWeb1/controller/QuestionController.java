package com.dipam.springDemoWeb1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dipam.springDemoWeb1.entity.Question;
import com.dipam.springDemoWeb1.entity.QuestionWrapper;
import com.dipam.springDemoWeb1.entity.Response;
import com.dipam.springDemoWeb1.service.QuestionService;

@RestController
@RequestMapping("question")
public class QuestionController {
	@Autowired
	QuestionService qs;
	@GetMapping("questions")
	public ResponseEntity<List<Question>> getAllQuestions() {
		return qs.getQuestions();
	}
	
	@PostMapping("questions")
	public String addQuestions(@RequestBody Question q) {
		qs.addAll(q);
		return "Question added";
	}
	
	@GetMapping("questions/{category}")
	public List<Question> getQuestionByCategory(@PathVariable String category){
		return qs.getByCategory(category);
	}
	
	@GetMapping("generate")
	public List<Integer> getQuestionsForQuiz(@RequestParam String category,@RequestParam int num){
		return qs.getQuestionsForQuiz(category,num);
	}
	
	@PostMapping("getQuestions")
	public List<QuestionWrapper> getQuestionsFromId(@RequestBody List<Integer> ques){
		return qs.getQuestionsFromId(ques);
	}
	
	@PostMapping("getScore")
	public int getScore(@RequestBody List<Response> res) {
		return qs.getScore(res);
	}
}
