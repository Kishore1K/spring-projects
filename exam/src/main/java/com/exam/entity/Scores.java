package com.exam.entity;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;

@Entity
public class Scores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer score;

    @ManyToOne
    private Users users;

    @ManyToOne
    private Exams exams;

}
