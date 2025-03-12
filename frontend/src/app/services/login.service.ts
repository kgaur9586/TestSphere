import { HttpClient } from '@angular/common/http';
import { Injectable, Inject, PLATFORM_ID } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import baseUrl from './helper';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  public loginStatusSubject = new Subject<boolean>();
  constructor(private http: HttpClient, @Inject(PLATFORM_ID) private platformId: Object) { }

  // Check if running in the browser
  private isBrowser(): boolean {
    return isPlatformBrowser(this.platformId);
  }

  public getCurrentUser() {
    return this.http.get(`${baseUrl}/current-user`);
  }

  public generateToken(loginData: any) {
    return this.http.post(`${baseUrl}/user/generate-token`, loginData);
  }

  public loginUser(token: any) {
    if (this.isBrowser()) {
      localStorage.setItem("token", token);
    }
    return true;
  }

  public isLoggedIn() {
    if (this.isBrowser()) {
      let tokenStr = localStorage.getItem("token");
      return tokenStr !== undefined && tokenStr !== '' && tokenStr !== null;
    }
    return false;
  }

  public logout() {
    if (this.isBrowser()) {
      localStorage.removeItem("token");
      localStorage.removeItem("user");
    }
    return true;
  }

  public getToken() {
    return this.isBrowser() ? localStorage.getItem("token") : null;
  }

  public setUser(user: any) {
    if (this.isBrowser()) {
      localStorage.setItem('user', JSON.stringify(user));
    }
  }

  public getUser() {
    if (this.isBrowser()) {
      let userStr = localStorage.getItem("user");
      return userStr ? JSON.parse(userStr) : null;
    }
    return null;
  }

  public getUserRole() {
    let user = this.getUser();
    return user ? user.authorities[0].authority : null;
  }
  
}
