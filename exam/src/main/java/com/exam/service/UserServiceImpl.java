package com.exam.service;

import com.exam.entity.*;
import com.exam.model.UpdateMarksDTO;
import com.exam.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Random;
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UsersRepos usersRepos;

    @Autowired
    private ExamsRepo examsRepo;

    @Autowired
    private QuestionsRepo questionsRepo;
    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private ScoresRepo scoresRepo;

    public String idGenerator(Users users){

        int id = new Random().nextInt(1000);
        String name = users.getName().toUpperCase().split(" ")[0];
        return  name+id;
    }


    @Override
    public Users register(Users users) {
        users.setUid(idGenerator(users));
        return usersRepos.save(users);
    }

    @Override
    public Exams register(Users users, Exams exams) {
        exams.setUser(users);
        return examsRepo.save(exams);
    }

    @Override
    public Users getUsers(String user) {
        return usersRepos.findByUid(user);
    }

    @Override
    public Exams getExams(Long examId) {
        return examsRepo.findById(examId).get();
    }

    @Override
    public Questions saveQuestions(Questions questions) {
        return questionsRepo.save(questions);
    }

    @Override
    public Students getStudent(String email) {
        return studentRepo.findByEmail(email);
    }

    @Override
    public Scores assignExam(Scores scores) {
        return scoresRepo.save(scores);
    }

    @Override
    public Students saveStudents(Students students) {
        return studentRepo.save(students);
    }

    @Override
    public Scores updateMarks(UpdateMarksDTO marksDTO) {
        Students students = studentRepo.findByEmail(marksDTO.getEmail());
        Exams exams = examsRepo.getReferenceById(marksDTO.getExamId());
        Scores scores = scoresRepo.getScores(exams.getId(), students.getId());
        scores.setScore(marksDTO.getScore());
        return  scores;
    }


}
