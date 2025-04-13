import { CommonModule } from '@angular/common';
import { Component, OnInit, Renderer2 } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { RouterLink } from '@angular/router';
import { LoginService } from '../../../services/login.service';

@Component({
  selector: 'app-user-profile',
  standalone: true,
  imports: [MatCardModule,MatButtonModule,CommonModule,RouterLink],
  templateUrl: './user-profile.component.html',
  styleUrl: './user-profile.component.css'
})
export class UserProfileComponent implements OnInit{
  user :any = null;
    constructor(private login: LoginService,private renderer: Renderer2){
  
    }
    ngOnInit(): void {
      this.user = this.login.getUser();
      console.log(this.user);
      this.login.loginStatusSubject.next(true);
    }

}
