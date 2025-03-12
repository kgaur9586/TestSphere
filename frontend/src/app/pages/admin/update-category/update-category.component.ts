import { Component, OnInit } from '@angular/core';
import { CategoryService } from '../../../services/category.service';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import {  RouterLink } from '@angular/router';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';

import { MatSnackBar } from '@angular/material/snack-bar';
import Swal from 'sweetalert2';
@Component({
  selector: 'app-update-category',
  standalone: true,
  imports: [MatCardModule,MatIconModule,MatListModule,RouterLink,FormsModule,MatFormFieldModule,CommonModule,MatInputModule,MatButtonModule],
  templateUrl: './update-category.component.html',
  styleUrl: './update-category.component.css'
})
export class UpdateCategoryComponent implements OnInit{
  
  category:any;
  catId:any;
  constructor(private categoryService:CategoryService,private router:Router,private route: ActivatedRoute){}
  
  ngOnInit(): void {
    this.catId = this.route.snapshot.params['cid'];
    this.categoryService.getCategory(this.catId).subscribe((data)=>{
      this.category = data;
    },(error)=>{
      console.log(error);
    })
  }

  updateCategory(category:any){
      Swal.fire({
                    icon:"warning",
                    title:"Are you sure ?",
                    confirmButtonText:'Update',
                    showCancelButton:true
                  }).then((result) => {
                    if(result.isConfirmed){
                      this.categoryService.updateCategory(category).subscribe((data)=>{
                        Swal.fire("Success!!","Category Details Updated Successfully",'success');
                        this.router.navigate([`/admin/categories`]);
                      },(error:any)=>{
                        console.log(error);
                        Swal.fire("Error","Error while updating the details",'error');
                      });
                    }
                  });
  }

}
