package com.exam.service;

import com.exam.entity.*;
import com.exam.model.UpdateMarksDTO;

public interface UserService {

    public Users register(Users users);

    public Exams register(Users users, Exams exams);

    Users getUsers(String user);

    Exams getExams(Long examId);

    Questions saveQuestions(Questions questions);

    Students getStudent(String email);

    Scores assignExam(Scores scores);

    Students saveStudents(Students students);

    Scores updateMarks(UpdateMarksDTO marksDTO);
}
