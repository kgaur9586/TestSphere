import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root'
})
export class NoramalGuard implements CanActivate {

  constructor(private router: Router,private login: LoginService) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): boolean | Observable<boolean> {
    if(this.login.isLoggedIn() && this.login.getUserRole() == "Normal"){
      return true;
    }
    this.router.navigate(['login']); 
    return false;
  }
}
