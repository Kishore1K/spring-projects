package com.exam.service;

import com.exam.entity.Exams;
import com.exam.entity.Questions;
import com.exam.entity.Users;
import com.exam.repository.ExamsRepo;
import com.exam.repository.QuestionsRepo;
import com.exam.repository.UsersRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UsersRepos usersRepos;

    @Autowired
    private ExamsRepo examsRepo;

    @Autowired
    private QuestionsRepo questionsRepo;

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


}
