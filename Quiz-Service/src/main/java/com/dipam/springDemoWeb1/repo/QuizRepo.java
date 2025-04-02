package com.dipam.springDemoWeb1.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dipam.springDemoWeb1.entity.Quiz;

public interface QuizRepo extends JpaRepository<Quiz, Integer> {

}
