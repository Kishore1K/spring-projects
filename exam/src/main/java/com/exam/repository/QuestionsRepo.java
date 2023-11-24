package com.exam.repository;

import com.exam.entity.Questions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionsRepo extends JpaRepository<Questions, Long> {
}
