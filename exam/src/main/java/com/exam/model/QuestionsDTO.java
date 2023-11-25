package com.exam.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionsDTO {
    private String question;
    private String option1;
    private  String option2;
    private String option3;
    private String option4;
    private int op_ans;

    private Long examId;
}
