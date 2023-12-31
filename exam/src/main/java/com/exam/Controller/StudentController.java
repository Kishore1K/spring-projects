package com.exam.Controller;

import com.exam.entity.Questions;
import com.exam.entity.Scores;
import com.exam.service.StudentService;
import com.exam.service.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @PostMapping("/exams")
    public ResponseEntity<List<Scores>> getExams(@RequestParam String email){
        return new ResponseEntity<>(studentService.findDetails(email), HttpStatus.OK);
    }

    @PostMapping("/quiz")
    public ResponseEntity<List<Questions>> getQuestions(@RequestParam("id") Long examId){
        return new ResponseEntity<>(studentService.getQuestions(examId), HttpStatus.OK);

    }
}
