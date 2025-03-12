

  import { Component, ElementRef, OnInit, Renderer2, ViewChild } from '@angular/core';
  import { NgForm, FormsModule } from '@angular/forms';
  import { CommonModule } from '@angular/common';
 
  import { UserService } from '../../services/user.service';
  import { HttpClientModule } from '@angular/common/http';
  import { MatSnackBar } from '@angular/material/snack-bar';
  
import { LoginService } from '../../services/login.service';
import { Router } from '@angular/router';
  @Component({
    selector: 'app-login',
    standalone: true,
    imports: [FormsModule,CommonModule, HttpClientModule],  // ✅ Add FormsModule to imports
    templateUrl: './login.component.html',
    styleUrl: './login.component.css'
  })
  export class LoginComponent implements OnInit {
      @ViewChild('shapesContainer') shapesContainer!: ElementRef;
      @ViewChild('registrationForm') registrationForm!: NgForm;
  
      loginData = {
        username:'',
        password:''
      };

      showPassword = false;
      strengthEmoji = '💪';
      showWelcome = false;
      isInvalid = false;
    
      constructor(private login:LoginService,private renderer: Renderer2, private userService:UserService,private snack:MatSnackBar,private router:Router) {}
    
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
  
      public user = {
        
        username:'',
        password:''
      }
  
      onSubmit() {
        if(this.user.username ==''|| this.user.username==null){
            this.snack.open("Username is required !!",'',{duration:3000,verticalPosition:'bottom'});
            
            return ;
        }
        if(this.user.password ==''|| this.user.password==null){
          this.snack.open("Password is required !!",'',{duration:3000,verticalPosition:'bottom'});
          
          return ;
      }
        // Request Server to generate token
      this.login.generateToken(this.user).subscribe(
        (data:any) =>{
          console.log("success");
          console.log(data);
          //login...
          this.login.loginUser(data.token);
          this.login.getCurrentUser().subscribe(
            (user:any) => {
              this.login.setUser(user);
              console.log(user);
              
              //redirect : if admin --> admin-dashboard , if noramal --> user-dashboard
              if(this.login.getUserRole() == "Admin"){
                //admin dashboard
                console.log("logged in as admin");
              
                this.router.navigate(['admin']);
                // window.location.href = '/admin';
                this.login.loginStatusSubject.next(true);
              }
              else if(this.login.getUserRole() == "Normal"){
                //normal dashboard
                // window.location.href = '/user';
                console.log("logged in as normal user");
                
                this.router.navigate([`user/${0}`]);
                this.login.loginStatusSubject.next(true);
              }
              else{
                console.log(this.login.getUserRole());
                this.login.logout();
                location.reload();
              }
            }
          )

        },(error) =>{
          console.log("error !!");
          console.log(error);
          this.snack.open("Incorrect User Details !!",'',{duration:3000,verticalPosition:'bottom'});
        }
      )
        
    }
  }
  