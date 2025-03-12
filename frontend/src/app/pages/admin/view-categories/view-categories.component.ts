import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { ActivatedRoute, Router, RouterLink, RouterModule } from '@angular/router';
import { CategoryService } from '../../../services/category.service';
import {MatDividerModule} from '@angular/material/divider';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-view-categories',
  standalone: true,
  imports: [MatCardModule, MatIconModule, RouterLink,RouterModule, MatListModule,CommonModule,MatDividerModule,MatButtonModule],
  templateUrl: './view-categories.component.html',
  styleUrl: './view-categories.component.css'
})
export class ViewCategoriesComponent implements OnInit{
  categories : any=null;
  constructor(private category:CategoryService,private router:Router){
    
  }


  ngOnInit(): void {
    this.category.categories().subscribe((data) => {
      this.categories = data;
      
    },(error) => {
      console.log(error);
    });
  }

  deleteCategory(catId:any){
    console.log(catId);
    Swal.fire({
              icon:"warning",
              title:"Are you sure ?",
              confirmButtonText:'Delete',
              showCancelButton:true
            }).then((result) => {
              if(result.isConfirmed){
                this.category.deleteCategory(catId).subscribe((data)=>{
                  Swal.fire("Success!!","Category Deleted Successfully",'success');
                  this.router.navigate([`/admin/categories`]);
                  location.reload();
                },(error:any)=>{
                  console.log(error);
                  Swal.fire("Error","Error while deleting the category",'error');
                });
              }
            });
  }

}
