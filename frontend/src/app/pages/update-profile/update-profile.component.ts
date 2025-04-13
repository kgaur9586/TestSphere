import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatListModule } from '@angular/material/list';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { UserService } from '../../services/user.service';
import { LoginService } from '../../services/login.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-update-profile',
  standalone: true,
  imports: [MatCardModule,MatListModule,MatButtonModule,CommonModule,RouterLink,FormsModule,MatFormFieldModule],
  templateUrl: './update-profile.component.html',
  styleUrl: './update-profile.component.css'
})
export class UpdateProfileComponent implements OnInit{
  id:any;
  user:any;
  constructor(private userService:UserService,private router:Router,private route:ActivatedRoute,private loginService:LoginService){}
  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.user= this.loginService.getUser();
  
  }

  public updateUser(user:any){
    Swal.fire({
              icon:"warning",
              title:"Are you sure ?",
              confirmButtonText:'Update',
              showCancelButton:true
            }).then((result) => {
              if(result.isConfirmed){
                this.userService.updateUser(user).subscribe((data)=>{
                  Swal.fire("Success!!","Profile Details Updated Successfully",'success');
                  this.loginService.setUser(this.user);
                  if(this.loginService.getUser().authorities[0].authority == "Admin"){
                    this.router.navigate([`/admin/profile/`]);
                  }
                  else{
                    this.router.navigate([`/user/user-profile`]);
                  }
                },(error:any)=>{
                  console.log(error);
                  Swal.fire("Error","Error while updating the details",'error');
                });
              }
            });
  }
}
