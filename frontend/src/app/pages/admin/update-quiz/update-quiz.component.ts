import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { QuizService } from '../../../services/quiz.service';
import { coerceStringArray } from '@angular/cdk/coercion';
import { MatCardModule } from '@angular/material/card';
import { MatSelectModule } from '@angular/material/select';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { CategoryService } from '../../../services/category.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-update-quiz',
  standalone: true,
  imports: [
    MatCardModule,
    MatFormFieldModule,
    MatSelectModule,
    MatIconModule,
    MatSelectModule,
    MatListModule,
    RouterLink,
    FormsModule,
    MatFormFieldModule,
    CommonModule,
    MatInputModule,
    MatButtonModule,
    MatSlideToggleModule,
  ],
  templateUrl: './update-quiz.component.html',
  styleUrl: './update-quiz.component.css',
})
export class UpdateQuizComponent implements OnInit {
  qId = 0;
  quiz: any;
  categories: any = null;
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private quizService: QuizService,
    private categoryService: CategoryService
  ) {}

  ngOnInit(): void {
    // this.categoryService.categories().subscribe((data : any) =>{
    //       this.categories = data;
    //     }, (error: any) =>{
    //       console.log(error);
    //       Swal.fire("Error","Error while loading the categories", 'error');
    //     })

    this.qId = this.route.snapshot.params['qid'];
    this.quizService.getQuizWithCategory(this.qId).subscribe(
      (data) => {
        console.log('get quiz with category', data);
        this.quiz = data;
        if (this.quiz.category && this.quiz.category.id) {
          this.quiz.categoryId = this.quiz.category.id;
        }
      },
      (error: any) => {
        console.log(error);
      }
    );
  }

  public updateQuiz(quiz: any) {
    console.log('updating quiz', quiz);
    this.quizService.update(quiz).subscribe(
      (data) => {
        Swal.fire('Success!!', 'Quiz is Updated successfully', 'success');
        this.router.navigate(['admin/quiz']);
      },
      (error: any) => {
        console.log(error);
        Swal.fire('Error!!', 'Some error occured', 'error');
      }
    );
  }
}
