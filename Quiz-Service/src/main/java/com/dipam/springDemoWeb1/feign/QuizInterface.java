package com.dipam.springDemoWeb1.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.dipam.springDemoWeb1.entity.QuestionWrapper;
import com.dipam.springDemoWeb1.entity.Response;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {
	@GetMapping("question/generate")
	public List<Integer> getQuestionsForQuiz(@RequestParam String category,@RequestParam int num);
	
	@PostMapping("question/getQuestions")
	public List<QuestionWrapper> getQuestionsFromId(@RequestBody List<Integer> ques);
	
	@PostMapping("question/getScore")
	public int getScore(@RequestBody List<Response> res);
}
