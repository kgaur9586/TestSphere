import { LocationStrategy } from '@angular/common';
import { Component, HostListener, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { QuestionService } from '../../../services/question.service';
import Swal from 'sweetalert2';
import { CommonModule } from '@angular/common';
import { MatListModule } from '@angular/material/list';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { FormsModule } from '@angular/forms';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { LoginService } from '../../../services/login.service';
import { QuizService } from '../../../services/quiz.service';
import { error } from 'console';

@Component({
  selector: 'app-start-quiz',
  standalone: true,
  imports: [CommonModule, MatListModule,RouterLink, MatProgressBarModule, MatButtonModule, MatCardModule, FormsModule],
  templateUrl: './start-quiz.component.html',
  styleUrl: './start-quiz.component.css'
})
export class StartQuizComponent implements OnInit {
  qId: any;
  questions: any;
  marksGot: any = 0;
  correctAnswers = 0;
  attempted = 0;
  isSubmit = false;
  totalMarks = 0;
  timer: any;
  interval: any;
  tabExitCount = 0;
  userId:any;

  constructor(
    private locationSt: LocationStrategy,
    private router: Router,
    private loginService: LoginService,
    private route: ActivatedRoute,
    private questionService: QuestionService,
    private quizService:QuizService
  ) {}

  ngOnInit(): void {
    this.qId = this.route.snapshot.params['qid'];
    this.userId = this.loginService.getUser().id;
    console.log(this.userId);
    this.loadQuestions(this.qId);
    this.forceFullScreen(); 
  }

  // Function to enforce full-screen mode
  forceFullScreen() {
    const elem = document.documentElement;
    if (elem.requestFullscreen) {
      elem.requestFullscreen();
    } else if ((elem as any).mozRequestFullScreen) {
      (elem as any).mozRequestFullScreen();
    } else if ((elem as any).webkitRequestFullscreen) {
      (elem as any).webkitRequestFullscreen();
    } else if ((elem as any).msRequestFullscreen) {
      (elem as any).msRequestFullscreen();
    }
  }

  
  // Add this HostListener to detect tab/window switches
@HostListener('document:visibilitychange')
handleVisibilityChange() {
  if(this.isSubmit){
    return;
  }
  if (document.hidden) {
    this.tabExitCount++;
    
    // Auto-submit on second exit
    if (this.tabExitCount >= 2) {
      Swal.fire('Quiz Auto-Submitted!', 'You switched tabs multiple times.', 'error');
      this.evalQuiz();
    }
  } else {
    // Show warning when returning after first exit
    if (this.tabExitCount === 1) {
      Swal.fire('Warning!', 'Do not switch tabs or windows! Next violation will auto-submit.', 'warning');
    }
  }
}

@HostListener('document:fullscreenchange')
handleFullScreenChange() {
  if(this.isSubmit){
    return;
  }
  if (!document.fullscreenElement) {
    this.tabExitCount++;

    if (this.tabExitCount === 1) {
      Swal.fire(
        'Warning!',
        'Please stay in fullscreen mode! Next violation will auto-submit.',
        'warning'
      ).then(() => this.forceFullScreen());
    } else if (this.tabExitCount >= 2) {
      Swal.fire('Quiz Auto-Submitted!', 'You exited fullscreen mode.', 'error');
      this.evalQuiz();
    }
  }
}


  loadQuestions(qId: any) {
    this.questionService.getQuestionsOfQuizForTest(qId).subscribe(
      (data) => {
        if (data) {
          this.questions = data;
          this.timer = this.questions.length * 2 * 60;
          this.startTimer();
        } else {
          this.questions = null;
        }
      },
      (error) => {
        console.log(error);
        Swal.fire('Error', 'Some error occurred while loading questions', 'error');
      }
    );
  }

  startTimer() {
    this.interval = window.setInterval(() => {
      if (this.timer <= 0) {
        this.evalQuiz();
        clearInterval(this.interval);
      } else {
        this.timer -= 1;
        if (this.isSubmit) {
          clearInterval(this.interval);
        }
      }
    }, 1000);
  }

 

  submitQuiz() {
    Swal.fire({
      title: 'Submit the quiz?',
      text: "You won't be able to revert this!",
      icon: 'info',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes, Submit!'
    }).then((result) => {
      if (result.isConfirmed) {
        this.isSubmit = true;
        this.evalQuiz();
      }
    });
  }

  evalQuiz() {

    this.attemptQuiz(this.qId,this.userId);
    document.exitFullscreen();
    this.questionService.evalQuiz(this.questions).subscribe(
      (data: any) => {
        this.marksGot = parseFloat(data.marksGot).toFixed(2);
        this.attempted = data.attempted;
        this.correctAnswers = data.correctAnswers;
        this.totalMarks = this.questions[0].quiz.maxMarks;
        this.isSubmit = true;
      },
      (error) => {
        console.log(error);
      }
    );
  }
  getMinutes(timer:any){
    return Math.floor(timer / 60);
  }

  printPage() {
    window.print();
  }
  tryAgain(){
    this.router.navigate([`/user/instructions/${this.qId}`]);
  }

  attemptQuiz(quizId:any,userId:any){
      this.quizService.attemptsQuiz(quizId,userId).subscribe((data:any)=>{
        console.log("Quiz attempted");
      },(error:any)=>{
        console.log(error);
      });
      
  }
}
