import { Component, OnInit } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list'; // ✅ Corrected MatList import
import { RouterModule } from '@angular/router';
import { CategoryService } from '../../../services/category.service';
import { defaultEquals } from '@angular/core/primitives/signals';
import { MatSnackBar } from '@angular/material/snack-bar';
import { error } from 'console';
import { CommonModule } from '@angular/common';
import { LoginService } from '../../../services/login.service';

@Component({
  selector: 'app-sidebar',
  standalone: true,
  imports: [MatCardModule, MatIconModule, RouterModule, MatListModule,CommonModule], // ✅ Use MatListModule
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.css'
})
export class SidebarComponent implements OnInit{
  isLoggedIn: any;
  constructor(private loginService:LoginService){}
  ngOnInit(): void {
    if(this.loginService.isLoggedIn()){
      this.isLoggedIn = true;
    }
  }
  logout(){
    this.loginService.logout();
    window.location.reload();
  }
  
 }
