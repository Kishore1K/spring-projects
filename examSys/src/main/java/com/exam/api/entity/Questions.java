package com.exam.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Questions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;

    private String option1;
    private String option2;
    private String option3;

    private String option4;

    private int op_ans;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Quiz> quizIDs = new ArrayList<>();
}
