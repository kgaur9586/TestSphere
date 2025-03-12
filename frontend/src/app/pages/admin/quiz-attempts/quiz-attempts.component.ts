import { Component, OnInit } from '@angular/core';
import { QuizService } from '../../../services/quiz.service';
import { ActivatedRoute } from '@angular/router';
import { CommonModule, DatePipe } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatListModule } from '@angular/material/list';
import { MatIconModule } from '@angular/material/icon';
import { error } from 'console';

@Component({
  selector: 'app-quiz-attempts',
  standalone: true,
  imports: [DatePipe,MatCardModule,MatListModule,MatIconModule,CommonModule],
  templateUrl: './quiz-attempts.component.html',
  styleUrl: './quiz-attempts.component.css'
})
export class QuizAttemptsComponent implements OnInit{
  qId:any;
  attempts:any;
  quizTitle:any;
  constructor(private quizService:QuizService,private route:ActivatedRoute){}
  ngOnInit(): void {
    this.qId = this.route.snapshot.params['qid'];
    this.quizService.getQuiz(this.qId).subscribe((data:any)=>{
      this.quizTitle = data.title;
    },(error) => {
      console.log(error);
    })
    this.quizService.getAllAttemptsOfQuiz(this.qId).subscribe((data:any) => {
      this.attempts = data;
      console.log(this.attempts);
    },(error:any)=>{
      console.log(error);
    })
  }

}
