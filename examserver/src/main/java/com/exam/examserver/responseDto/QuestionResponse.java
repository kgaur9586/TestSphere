package com.exam.examserver.responseDto;

import com.exam.examserver.entities.exam.Quiz;
import com.exam.examserver.entities.exam.Question;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuestionResponse {
    private Quiz quiz;
    private List<Question> questions;
}