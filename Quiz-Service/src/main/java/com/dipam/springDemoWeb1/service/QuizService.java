package com.dipam.springDemoWeb1.service;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dipam.springDemoWeb1.entity.QuestionWrapper;
import com.dipam.springDemoWeb1.entity.Quiz;
import com.dipam.springDemoWeb1.entity.Response;
import com.dipam.springDemoWeb1.feign.QuizInterface;
import com.dipam.springDemoWeb1.repo.QuizRepo;

@Service
public class QuizService {
	@Autowired
	QuizRepo qr;
	@Autowired
	QuizInterface qr1;

	public void createQuiz(String category, int num, String title) {
		List<Integer> questions = qr1.getQuestionsForQuiz(category, num);
		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setQuestions(questions);
		qr.save(quiz);
	}

	public List<QuestionWrapper> getQuestions(int id) {

		Quiz quiz = qr.findById(id).get();
		List<Integer> ids = quiz.getQuestions();
		List<QuestionWrapper> questions = qr1.getQuestionsFromId(ids);
		return questions;
	}

	public int submitQuiz(int id, List<Response> res) {

		Integer result=qr1.getScore(res);
		return result;
	}

}
