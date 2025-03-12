import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterLink, RouterModule } from '@angular/router';
import { log } from 'console';
import { QuestionService } from '../../../services/question.service';
import { MatCard, MatCardActions, MatCardContent, MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { MatButtonModule } from '@angular/material/button';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { MatDividerModule } from '@angular/material/divider';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-view-questions',
  standalone: true,
  imports: [MatCardModule,MatCard,MatCardActions,MatCardContent,MatIconModule,MatListModule,MatButtonModule,RouterLink,FormsModule,CommonModule,RouterModule,MatDividerModule],
  templateUrl: './view-questions.component.html',
  styleUrl: './view-questions.component.css'
})
export class ViewQuestionsComponent implements OnInit{
  qId:any;
  qTitle:any;
  questions : any=[];
  constructor(private route:ActivatedRoute,private router : Router,private questionService:QuestionService){}


  ngOnInit(): void {
    this.qId = this.route.snapshot.params['qid'];
    this.qTitle = this.route.snapshot.params['title'];
    this.questionService.getQuestionsOfQuiz(this.qId).subscribe((data)=>{
      console.log(data);
      this.questions = data;
    },(error:any)=>{
      console.log(error);
    })
  }
  public deleteQuestion(qid:any){
        Swal.fire({
          icon:"warning",
          title:"Are you sure ?",
          confirmButtonText:'Delete',
          showCancelButton:true
        }).then((result) => {
          if(result.isConfirmed){
            this.questionService.delete(qid).subscribe((data : any)=>{
              Swal.fire("Success!!","Question is Deleted successfully",'success');
              this.router.navigate([`/admin/view-questions/${this.qId}/${this.qTitle}`]);
              window.location.reload();
            },(error:any)=>{
              console.log(error);
              Swal.fire("Error","Error while deleting the quiz",'error');
            });
          }
        })
    }

}
