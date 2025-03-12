import { Component, OnInit } from '@angular/core';
import { CommonModule, Location } from '@angular/common';

import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatListModule } from '@angular/material/list';
import { MatSelectModule } from '@angular/material/select';
import { ActivatedRoute, Router } from '@angular/router';
import { QuizService } from '../../../services/quiz.service';
import { QuestionService } from '../../../services/question.service';
import Swal from 'sweetalert2';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-update-question',
  standalone: true,
  imports: [CommonModule,MatCardModule,MatListModule,MatButtonModule,MatInputModule,MatSelectModule,MatFormFieldModule,MatInputModule,FormsModule,MatSelectModule],
  templateUrl: './update-question.component.html',
  styleUrl: './update-question.component.css'
})
export class UpdateQuestionComponent implements OnInit{
  quesId:any;
  question : any = {
    quiz:{
      
    },
    content:'',
    option1:'',
    option2:'',
    option3:'',
    option4:'',
    answer:''
  }
  constructor(private route: ActivatedRoute,private location:Location, private questionService:QuestionService,private router:Router){}


  ngOnInit(): void {
    this.quesId = this.route.snapshot.params['quesId'];
    this.questionService.getQuestion(this.quesId).subscribe((data)=>{
      this.question = data;
    })
  }
  updateQuestion(question:any){
    this.questionService.updateQuestion(question).subscribe((data:any) => {
          Swal.fire("Success!!","Question is Updated successfully",'success');
          this.location.back()
        },(error:any) => {
          console.log(error);
          Swal.fire("Error!!","Some error occured",'error');
        })
  }

}
