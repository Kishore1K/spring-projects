package com.exam.Controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @PostMapping("/exams")
    public String getExams(@RequestParam String email){
        return
    }
}
