package com.dipam.springDemoWeb1.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dipam.springDemoWeb1.entity.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
	List<Question> findByCategory(String category);

	@Query(value="SELECT q.id FROM Question q WHERE q.category=:category ORDER BY RAND() LIMIT :num",nativeQuery=true)
	List<Integer> findRandomQuestionByCategory(String category, int num);
}
