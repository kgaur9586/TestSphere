import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { Router, RouterLink } from '@angular/router';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { CategoryService } from '../../../services/category.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-category',
  standalone: true,
  imports: [MatCardModule,MatIconModule,MatListModule,RouterLink,FormsModule,MatFormFieldModule,CommonModule,MatInputModule,MatButtonModule],
  templateUrl: './add-category.component.html',
  styleUrl: './add-category.component.css'
})
export class AddCategoryComponent {

  public category={
    title:'',
    description:''
  }
  constructor(private service:CategoryService,private snack:MatSnackBar,private router:Router){}
  public formSubmit(){
    if(this.category.title.trim() == '' || this.category.title == null){
      this.snack.open("Title is required !!",'',{duration:3000,verticalPosition:'top'});
      return;
    }
    this.service.addCategory(this.category).subscribe((data:any) => {
      Swal.fire("Success!!","Category is added successfully",'success');
      this.router.navigate(['admin/categories']);
      return ;
    },(error:any)=>{
      console.log(error);
    });
  }
}
