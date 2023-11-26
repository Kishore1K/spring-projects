package com.exam.service;

import com.exam.entity.Exams;
import com.exam.entity.Questions;
import com.exam.entity.Scores;

import java.util.List;

public interface StudentService {
    List<Scores> findDetails(String email);

    List<Questions> getQuestions(Long examId);
}
