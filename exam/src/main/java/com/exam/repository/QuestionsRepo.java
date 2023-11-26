package com.exam.repository;

import com.exam.entity.Exams;
import com.exam.entity.Questions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionsRepo extends JpaRepository<Questions, Long> {
    List<Questions> findByExams(Exams exams);
}
