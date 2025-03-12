import { CommonModule } from '@angular/common';
import { Component, ElementRef,HostListener, OnInit, ViewChild } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { QuizService } from '../../services/quiz.service';
import { CategoryService } from '../../services/category.service';
import { error } from 'console';


@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule,MatListModule,MatCardModule,MatButtonModule,MatIconModule,RouterLink],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit{
  
  quizzes: any;
  category:any;
  categoryQuizzes: { [key: string]: any[] } = {};

    constructor(private route:ActivatedRoute,private quizService:QuizService,private catService:CategoryService){}
    ngOnInit(): void {
      this.quizService.getActiveQuizzes().subscribe((data:any) => {
      this.quizzes = data;
    },(error:any)=> {
            console.log(error);
        });
        this.catService.categories().subscribe((data:any)=>{
          this.category = data;
          this.loadQuizzesForCategories();
        },(error:any)=>{
          console.log(error);
        });
    }
    loadQuizzesForCategories() {
      if (!this.category) return;
      this.category.forEach((c: any) => {
        if (c.cid) {
          this.quizService.getActiveQuizzesOfCategory(c.cid).subscribe(
            (data: any) => {
              if(data != null && data.length > 0){

                this.categoryQuizzes[c.cid] = this.categoryQuizzes[c.cid] || [];
                this.categoryQuizzes[c.cid] = data;
              }
              
            },
            (error: any) => {
              console.log(error);
            }
          );
        }
      });
    }
  

  @ViewChild('scrollContainer', { static: false }) scrollContainer!: ElementRef;
  scrollLeft() {
    this.scrollContainer.nativeElement.scrollBy({ left: -280, behavior: 'smooth' });
  }

  // Scroll right
  scrollRight() {
    this.scrollContainer.nativeElement.scrollBy({ left: 280, behavior: 'smooth' });
  }
  
}
