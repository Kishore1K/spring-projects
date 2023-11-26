package com.exam.service;

import com.exam.entity.Scores;
import com.exam.repository.QuestionsRepo;
import com.exam.repository.ScoresRepo;
import com.exam.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private ScoresRepo scoresRepo;
    @Autowired
    private QuestionsRepo questionsRepo;
}
