import { HTTP_INTERCEPTORS, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { LoginService } from "./login.service";



@Injectable()
export class AuthInterceptor implements HttpInterceptor{
    constructor(private login:LoginService){
        
    }
    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        //add the jwt token(local storage) request
        console.log("inside interceptor");
        const token = this.login.getToken();
        let authRequest = req;

        if(token != null){
                authRequest = authRequest.clone({setHeaders:{Authorization:`Bearer ${token}`}});
        }
        return next.handle(authRequest);
        
    }
    
}

export const authInterceptorProviders = [
    {
        provide:HTTP_INTERCEPTORS,
        useClass: AuthInterceptor,
        multi: true
    }
];