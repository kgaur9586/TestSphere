package com.exam.examserver.responseDto;

import com.exam.examserver.entities.exam.QuizAttempt;
import com.exam.examserver.entities.User;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuizAttemptResponse {
    private String id;
    private String quizId;
    private String userId;
    private LocalDateTime attemptTime;
    private User user;

    public QuizAttemptResponse(QuizAttempt attempt, User user) {
        this.id = attempt.getId();
        this.quizId = attempt.getQuizId();
        this.userId = attempt.getUserId();
        this.attemptTime = attempt.getAttemptTime();
        this.user = user;
    }
}
