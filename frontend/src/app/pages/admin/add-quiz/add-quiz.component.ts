import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatListModule } from '@angular/material/list';
import { MatSelectModule } from '@angular/material/select';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { Router, RouterLink } from '@angular/router';
import { CategoryService } from '../../../services/category.service';
import Swal from 'sweetalert2';
import { QuizService } from '../../../services/quiz.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-add-quiz',
  standalone: true,
  imports: [
    MatCardModule,
    MatFormFieldModule,
    MatSelectModule,
    MatIconModule,
    MatListModule,
    RouterLink,
    FormsModule,
    CommonModule,
    MatInputModule,
    MatButtonModule,
    MatSlideToggleModule
  ],
  templateUrl: './add-quiz.component.html',
  styleUrl: './add-quiz.component.css'
})
export class AddQuizComponent implements OnInit {
  categories: any = null;
  quizData = {
    title: '',
    description: '',
    maxMarks: '',
    numberOfQuestions: '',
    active: true,
    category: null
  };

  constructor(
    private service: CategoryService,
    private quizService: QuizService,
    private router: Router,
    private snack: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.service.categories().subscribe(
      (data: any) => {
        this.categories = data;
        console.log(this.categories);
      },
      (error: any) => {
        console.log(error);
        Swal.fire('Error', 'Error while loading the categories', 'error');
      }
    );
  }

  public submitQuiz() {
    if (this.quizData.title.trim() === '' || this.quizData.title == null) {
      this.snack.open('Title is required !!', '', {
        duration: 3000,
        verticalPosition: 'top'
      });
      return;
    }
    this.quizService.addQuiz(this.quizData).subscribe(
      (data) => {
        Swal.fire('Success!!', 'Quiz is added successfully', 'success');
        this.router.navigate(['admin/quiz']);
      },
      (error: any) => {
        console.log(error);
      }
    );
  }
}
