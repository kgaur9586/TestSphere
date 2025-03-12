import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { QuizService } from '../../../services/quiz.service';
import { error } from 'console';
import { CommonModule } from '@angular/common';
import { MatCardActions, MatCardModule } from '@angular/material/card';
import { MatListModule } from '@angular/material/list';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-load-quiz',
  standalone: true,
  imports: [CommonModule,MatCardModule,MatListModule,MatCardActions,MatButtonModule,RouterModule],
  templateUrl: './load-quiz.component.html',
  styleUrl: './load-quiz.component.css'
})
export class LoadQuizComponent implements OnInit{
  catId:any;
  quizzes: any;
  constructor(private route:ActivatedRoute,private quizService:QuizService){}
  ngOnInit(): void {
    this.route.params.subscribe((params:any)=>{
      this.catId = params.cId;
      console.log(this.catId);
      if(this.catId == 0){
        this.quizService.getActiveQuizzes().subscribe((data:any) => {
          this.quizzes = data;
          console.log(this.quizzes);
        },(error:any)=> {
          console.log(error);
          alert("error in loading quizzes!");
        });
      }
      else{
        console.log("load specific quiz..");
        this.quizService.getActiveQuizzesOfCategory(this.catId).subscribe((data)=>{
          this.quizzes = data;
          console.log(this.quizzes);
        },(error:any)=>{
          console.log(error);
          alert("error in loading data..");
        })
      }
      
    }
  )}
    
}
