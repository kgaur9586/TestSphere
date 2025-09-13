import { Component, OnInit } from '@angular/core';
import { MatIcon } from '@angular/material/icon';
import { MatToolbar } from '@angular/material/toolbar';
import { Router, RouterModule } from '@angular/router';
import { LoginService } from '../../services/login.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [MatToolbar, MatIcon, RouterModule,CommonModule],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent implements OnInit {
  isLoggedIn = false;
  user: any = null;
  admin: any = false;
  normalUser: any = false;

  constructor(public login: LoginService, private router: Router) {}

  ngOnInit(): void {
    this.isLoggedIn = this.login.isLoggedIn();
    this.user = this.login.getUser();

    if (this.user?.roles.length > 0) {
      if (this.user.roles[0] === 'Normal') {
        this.normalUser = true;
      } else {
        this.admin = true;
      }
    }

    this.login.loginStatusSubject.asObservable().subscribe(() => {
      this.isLoggedIn = this.login.isLoggedIn();
      this.user = this.login.getUser();

      if (this.user?.roles.length > 0) {
        if (this.user.roles[0] === 'Normal') {
          this.normalUser = true;
          this.admin = false;
        } else {
          this.admin = true;
          this.normalUser = false;
        }
      } else {
        this.admin = false;
        this.normalUser = false;
      }
    });
  }

  public logout() {
    this.login.logout();
    location.reload();
  }

  navigateToUser() {
    this.router.navigate([`/user/${0}`]);
  }
}
