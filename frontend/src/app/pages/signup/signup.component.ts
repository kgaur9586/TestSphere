import { Component, ElementRef, OnInit, Renderer2, ViewChild } from '@angular/core';
import { NgForm, FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import confetti from 'canvas-confetti';
import { UserService } from '../../services/user.service';
import { HttpClientModule } from '@angular/common/http';
import { MatSnackBar } from '@angular/material/snack-bar';
import Swal from 'sweetalert2';
@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [FormsModule,CommonModule, HttpClientModule],  // ✅ Add FormsModule to imports
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css'
})
export class SignupComponent implements OnInit {
    @ViewChild('shapesContainer') shapesContainer!: ElementRef;
    @ViewChild('registrationForm') registrationForm!: NgForm;

    
    showPassword = false;
    strengthEmoji = '💪';
    showWelcome = false;
    isInvalid = false;
  
    constructor(private renderer: Renderer2, private userService:UserService,private snack:MatSnackBar) {}
  
    ngOnInit() {}

    ngAfterViewInit() {  
      this.createFloatingShapes();  // ✅ Move to ngAfterViewInit
    }

    createFloatingShapes() {
      for (let i = 0; i < 20; i++) {
        const shape = this.renderer.createElement('div');
        this.renderer.addClass(shape, 'float');
        this.renderer.setStyle(shape, 'width', `${Math.random() * 50 + 20}px`);
        this.renderer.setStyle(shape, 'height', `${Math.random() * 50 + 20}px`);
        this.renderer.setStyle(shape, 'left', `${Math.random() * 100}%`);
        this.renderer.setStyle(shape, 'top', `${Math.random() * 100}%`);
        this.renderer.setStyle(shape, 'animationDelay', `${Math.random() * 5}s`);
        this.renderer.appendChild(this.shapesContainer.nativeElement, shape);
      }
    }

    togglePassword() {
      this.showPassword = !this.showPassword;
    }

    updatePasswordStrength(event: Event) {  
      const inputElement = event.target as HTMLInputElement;
      if (!inputElement) return;
  
      const strength = inputElement.value.length;
      if (strength === 0) this.strengthEmoji = '💪';
      else if (strength < 5) this.strengthEmoji = '😰';
      else if (strength < 8) this.strengthEmoji = '😅';
      else this.strengthEmoji = '🔐';
    }

    public user = {
      firstName:'',
      lastName:'',
      email:'',
      username:'',
      password:''
    }

    onSubmit() {
      if(this.user.username ==''|| this.user.username==null){
          this.snack.open("Username is required !!",'',{duration:3000,verticalPosition:'bottom'});
          
          return ;
      }
      const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    
      if(!regex.test(this.user.email)){
        this.snack.open("Please enter a valid email",'',{duration:2000});
        return ;
      }
      if (this.registrationForm.valid) {
        
        console.log("submitting user : ",this.user);
        

        //addUser: userService
        this.userService.addUser(this.user).subscribe(
          (data: any)=>{
            //success
            confetti({
              particleCount: 100,
              spread: 70,
              origin: { y: 0.6 }
            });
            console.log("Response data : ",data);
            this.showWelcome = true;
            this.registrationForm.resetForm();
            window.location.href = "/login";
            Swal.fire("Registration Successful!");
          },
          (error: any)=>{
            //error
            console.log(error);
            Swal.fire("Username or Email already exists !");
          }
        )
      } else {
        this.isInvalid = true;
        setTimeout(() => this.isInvalid = false, 500);
      }
  }
}
