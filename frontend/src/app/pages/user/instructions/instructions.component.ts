import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { QuizService } from '../../../services/quiz.service';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatActionList, MatListModule } from '@angular/material/list';
import { MatButtonModule } from '@angular/material/button';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-instructions',
  standalone: true,
  imports: [CommonModule,MatCardModule,MatListModule,MatActionList,MatButtonModule,RouterLink],
  templateUrl: './instructions.component.html',
  styleUrl: './instructions.component.css'
})
export class InstructionsComponent implements OnInit{
  qId:any;
  quiz:any;
  constructor(private route: ActivatedRoute,private quizService:QuizService,private router:Router){}
  ngOnInit(): void {
    this.qId = this.route.snapshot.params['qid'];
    this.quizService.getQuiz(this.qId).subscribe((data)=>{
      this.quiz = data;
      console.log(this.quiz);
    },(error:any)=>{
      console.log(error);
      alert("Error while loading data...");
    })
  }
  startQuiz(){
    Swal.fire({
      title: "Do you want to start the quiz ?",
      text: "You won't be able to revert this!",
      icon: "info",
      showCancelButton: true,
      confirmButtonColor: "#3085d6",
      cancelButtonColor: "#d33",
      confirmButtonText: "Yes, Start !"
    }).then((result) => {
      if (result.isConfirmed) {
        this.router.navigate(['/user/start-quiz/'+this.qId]);
      }
    });
  }

}
