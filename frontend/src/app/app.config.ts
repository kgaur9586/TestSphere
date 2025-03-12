import { APP_INITIALIZER, ApplicationConfig, importProvidersFrom } from '@angular/core';
import { provideRouter } from '@angular/router';
import { routes } from './app.routes';
import { provideClientHydration } from '@angular/platform-browser';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { provideHttpClient, withFetch, withInterceptorsFromDi } from '@angular/common/http';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { AuthInterceptor } from './services/auth.interceptor';
import { LoginService } from './services/login.service';
import { NgxUiLoaderHttpModule, NgxUiLoaderModule } from 'ngx-ui-loader';

export function initializeApp(loginService: LoginService) {
  return () => loginService.getUser();
}
export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes),
    provideClientHydration(),
    provideAnimationsAsync(),
    importProvidersFrom(
      NgxUiLoaderModule,
      NgxUiLoaderHttpModule.forRoot({
        showForeground: true
      })),
    provideHttpClient(withFetch(), withInterceptorsFromDi()),  // Ensure HTTP interceptors are enabled
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true } ,
    {
      provide: APP_INITIALIZER,
      useFactory: initializeApp,
      deps: [LoginService],
      multi: true
    }
  ]
};
