import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { ActivatedRoute, Router } from '@angular/router';
import { QuizService } from '../../../services/quiz.service';
import { QuestionService } from '../../../services/question.service';
import Swal from 'sweetalert2';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-add-question',
  standalone: true,
  imports: [
    CommonModule,
    MatCardModule,
    MatButtonModule,
    MatInputModule,
    MatSelectModule,
    MatFormFieldModule,
    FormsModule
  ],
  templateUrl: './add-question.component.html',
  styleUrl: './add-question.component.css'
})
export class AddQuestionComponent implements OnInit {
  qId: any;
  q_title: any;
  question: any = {
    quiz: {},
    content: '',
    option1: '',
    option2: '',
    option3: '',
    option4: '',
    answer: ''
  };

  constructor(
    private route: ActivatedRoute,
    private snack: MatSnackBar,
    private router: Router,
    private quizService: QuizService,
    private questionService: QuestionService
  ) {}

  ngOnInit(): void {
    this.qId = this.route.snapshot.params['qid'];
    this.question.quiz['qid'] = this.qId;
    this.q_title = this.route.snapshot.params['title'];

    console.log(this.q_title);
  }

  public addQuestion(question: any) {
    if (question.answer.trim() === '' || question.answer == null) {
      this.snack.open('Please Select answer!!', '', {
        duration: 3000,
        verticalPosition: 'top'
      });
      return;
    }
    this.questionService.addQuestionTOQuiz(question).subscribe(
      (data) => {
        Swal.fire('Successful', 'Question is added successfully !', 'success');
        this.router.navigate([`admin/view-questions/${this.qId}/${this.q_title}`]);
      },
      (error: any) => {
        Swal.fire('Error!', 'Some error occurred, try again!', 'error');
      }
    );
  }
}
