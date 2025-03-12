import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatListItem, MatListModule } from '@angular/material/list';
import { RouterLink } from '@angular/router';
import { CategoryService } from '../../../services/category.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-user-sidebar',
  standalone: true,
  imports: [MatListItem,MatButtonModule,MatListModule,MatIconModule,RouterLink,CommonModule,MatCardModule,RouterLink],
  templateUrl: './user-sidebar.component.html',
  styleUrl: './user-sidebar.component.css'
})
export class UserSidebarComponent implements OnInit{
  categories :any=null;
  constructor(private categoryService:CategoryService,private snack:MatSnackBar){}
  ngOnInit(): void {
  
    this.categoryService.categories().subscribe((data)=>{
      this.categories = data;
      console.log(this.categories);
    },(error:any) =>{
      this.snack.open("Error in loading categories",'',{duration:3000});
      console.log(error);
    })
  }

}
