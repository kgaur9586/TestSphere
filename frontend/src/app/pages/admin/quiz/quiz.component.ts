import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatCardActions, MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { QuizService } from '../../../services/quiz.service';
import Swal from 'sweetalert2';
import { Router, RouterLink } from '@angular/router';
import { info } from 'console';
import { QuestionService } from '../../../services/question.service';

@Component({
  selector: 'app-quiz',
  standalone: true,
  imports: [MatCardModule,MatIconModule,MatListModule,MatButtonModule,CommonModule,MatCardActions,RouterLink],
  templateUrl: './quiz.component.html',
  styleUrl: './quiz.component.css'
})
export class QuizComponent implements OnInit{
  quizzes : any=null;
  questionCounts: { [key: number]: number } = {};
  constructor(private service:QuizService,private questionService:QuestionService,private router:Router){}
  
  
  ngOnInit(): void {
    this.service.quizzes().subscribe((data:any) => {
        this.quizzes = data;
        // this.quizzes.forEach((q: { qid: any; }) => {
        //   this.fetchNoOfQuestions(q.qid);
        // });
    },(error: any) => {
      console.log(error);
      Swal.fire("Error","Error while loading the quizzes",'error');
    })
  }

  // fetchNoOfQuestions(qid: any) {
  //   if (this.questionCounts[qid] !== undefined) {
  //     return; // If already fetched, do nothing
  //   }

  //   this.questionService.getQuestionsOfQuiz(qid).subscribe(
  //     (data: any) => {
  //       if (Array.isArray(data)) {
  //         this.questionCounts[qid] = data.length; // Store count
  //       } else {
  //         console.error("Expected array but got:", data);
  //       }
  //     },
  //     (error: any) => {
  //       console.log(error);
  //     }
  //   );
  // }
  

  public deleteQuiz(qid:any){
      Swal.fire({
        icon:"info",
        title:"Are you sure ?",
        confirmButtonText:'Delete',
        showCancelButton:true
      }).then((result) => {
        if(result.isConfirmed){
          this.service.delete(qid).subscribe((data)=>{
            Swal.fire("Success!!","Quiz is Deleted successfully",'success');
            this.router.navigate(['/admin']);
          },(error:any)=>{
            console.log(error);
            Swal.fire("Error","Error while deleting the quiz",'error');
          });
        }
      })
  }





}
