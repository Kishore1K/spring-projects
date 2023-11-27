package com.exam.Controller;

import com.exam.entity.*;
import com.exam.model.AssignDTO;
import com.exam.model.ExamRegister;
import com.exam.model.QuestionsDTO;
import com.exam.model.UpdateMarksDTO;
import com.exam.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
public class MainController {

    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper modelMapper;
    @GetMapping({"", "/"})
    public String welcome(){
        return "hello World";
    }


    @PostMapping("/save")
    public ResponseEntity<Users> saveUser(@RequestBody Users users){
        System.out.println(users);
        return  new ResponseEntity<>(userService.register(users), HttpStatus.CREATED);
    }

    @PostMapping("/exam")
    public ResponseEntity<Exams> saveExam(@RequestBody ExamRegister exams){
        Users users = userService.getUsers(exams.getUser());
        Exams exams1 = modelMapper.map(exams, Exams.class);
        exams1.setUser(users);
        return  new ResponseEntity<>(userService.register(users,exams1), HttpStatus.CREATED);
    }

    @PostMapping("/Question")
    public ResponseEntity<Questions> saveQuestions(@RequestBody QuestionsDTO questionsDTO){
        Exams exams = userService.getExams(questionsDTO.getExamId());
        Questions questions = modelMapper.map(questionsDTO, Questions.class);
        questions.setExams(exams);
        System.out.println(questions);
        return new ResponseEntity<>(userService.saveQuestions(questions), HttpStatus.CREATED);

    }

    @PostMapping("/student")
    public ResponseEntity<Students> saveStudents(@RequestBody Students students){
        return  new ResponseEntity<>(userService.saveStudents(students), HttpStatus.CREATED);
    }

    @PostMapping("/exams")
    public ResponseEntity<Scores> assignExam(@RequestBody AssignDTO assignDTO){

        Students students  = userService.getStudent(assignDTO.getEmail());
        Exams exams = userService.getExams(assignDTO.getExamId());
        Scores scores = new Scores();
        scores.setExams(exams);
        scores.setStudents(students);
        System.out.println(scores);
        return  new ResponseEntity<>(userService.assignExam(scores), HttpStatus.CREATED);
    }

    @PutMapping("/updateMarks")
    public  ResponseEntity<Scores> updateScore(@RequestBody UpdateMarksDTO marksDTO){
        return  new ResponseEntity<>(userService.updateMarks(marksDTO), HttpStatus.ACCEPTED);
    }

    public ResponseEntity<Exams> updateStatus(@RequestParam Boolean status, @RequestParam Long examId){
        return  new ResponseEntity<>(userService.updateStatus(examId, status), HttpStatus.OK);
    }


}
