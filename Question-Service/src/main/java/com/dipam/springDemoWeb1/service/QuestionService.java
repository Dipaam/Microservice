package com.dipam.springDemoWeb1.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dipam.springDemoWeb1.entity.Question;
import com.dipam.springDemoWeb1.entity.QuestionWrapper;
import com.dipam.springDemoWeb1.entity.Response;
import com.dipam.springDemoWeb1.repo.QuestionRepository;

@Service
public class QuestionService {
	@Autowired
	QuestionRepository repo;
	public ResponseEntity<List<Question>> getQuestions() {
		try {
			return new ResponseEntity<>(repo.findAll(),HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
	}
	public void addAll(Question q) {
		repo.save(q);
	}
	public List<Question> getByCategory(String category) {
		return repo.findByCategory(category);
	}
	public List<Integer> getQuestionsForQuiz(String category, int num) {
		List<Integer> questions=repo.findRandomQuestionByCategory(category,num);
		return questions;
	}
	public List<QuestionWrapper> getQuestionsFromId(List<Integer> quesId) {
		List<QuestionWrapper> wrapper=new ArrayList<>();
		List<Question> question=new ArrayList<>();
		for(Integer i:quesId) {
			question.add(repo.findById(i).get());
		}
		
		for(Question q:question) {
			QuestionWrapper qr=new QuestionWrapper();
			qr.setId(q.getId());
			qr.setQuestionTitle(q.getQuestionTitle());
			qr.setOption1(q.getOption1());
			qr.setOption2(q.getOption2());
			qr.setOption3(q.getOption3());
			qr.setOption4(q.getOption4());
			wrapper.add(qr);
		}
		return wrapper;
	}
	public int getScore(List<Response> res) {
		int right=0;
		for(Response r:res) {
			Question question=repo.findById(r.getId()).get();
			if(r.getResponse().equals(question.getRightAnswer())) {
				right++;
			}
		}
		return right;
	}
	
}
