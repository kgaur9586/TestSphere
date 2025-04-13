import { Routes } from '@angular/router';
import { SignupComponent } from './pages/signup/signup.component';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { DashboardComponent } from './pages/admin/dashboard/dashboard.component';
import { UserDashboardComponent } from './pages/user/user-dashboard/user-dashboard.component';
import { AdminGuard } from './services/admin.guard';
import { NoramalGuard } from './services/normal.guard';
import { ProfileComponent } from './pages/profile/profile.component';
import { WelcomeComponent } from './pages/admin/welcome/welcome.component';
import { ViewCategoriesComponent } from './pages/admin/view-categories/view-categories.component';
import { AddCategoryComponent } from './pages/admin/add-category/add-category.component';
import { QuizComponent } from './pages/admin/quiz/quiz.component';
import { AddQuizComponent } from './pages/admin/add-quiz/add-quiz.component';
import { UpdateQuizComponent } from './pages/admin/update-quiz/update-quiz.component';
import { ViewQuestionsComponent } from './pages/admin/view-questions/view-questions.component';
import { AddQuestionComponent } from './pages/admin/add-question/add-question.component';
import { UpdateQuestionComponent } from './pages/admin/update-question/update-question.component';
import path from 'path';
import { LoadQuizComponent } from './pages/user/load-quiz/load-quiz.component';
import { InstructionsComponent } from './pages/user/instructions/instructions.component';
import { StartQuizComponent } from './pages/user/start-quiz/start-quiz.component';
import { UpdateProfileComponent } from './pages/update-profile/update-profile.component';
import { UpdateCategoryComponent } from './pages/admin/update-category/update-category.component';
import { combineLatest } from 'rxjs';
import { Component } from '@angular/core';
import { QuizAttemptsComponent } from './pages/admin/quiz-attempts/quiz-attempts.component';
import { UserProfileComponent } from './pages/user/user-profile/user-profile.component';
export const routes: Routes = [
    {
        path:'user/start-quiz/:qid',component:StartQuizComponent,
        canActivate:[NoramalGuard],
    },
    {
        path:'signup',component:SignupComponent,pathMatch:"full",
        
    },{
        path:'',component:HomeComponent,
    },{
        path:'login',component:LoginComponent,pathMatch:"full"
    },{
        path:'admin',component:DashboardComponent,
        children:[
            {
                path:"",component:WelcomeComponent,
            },
            {
                path:"profile",component:ProfileComponent,
            },
            {
                path:'update/:id',component:UpdateProfileComponent,
                pathMatch:'full'
            },
            {
                path:"categories",component:ViewCategoriesComponent
            },
            {
                path:'update-category/:cid',component:UpdateCategoryComponent
            },
            {
                path:"add-categories",component:AddCategoryComponent
            },
            {
                path:"quiz",component:QuizComponent
            },
            {
                path:"add-quiz",component:AddQuizComponent
            },
            {
                path:"update-quiz/:qid",component:UpdateQuizComponent
            },
            {
                path:"quiz-attempt/:qid",component:QuizAttemptsComponent
            },
            {
                path:"view-questions/:qid/:title",component:ViewQuestionsComponent
            },
            {
                path:"add-question/:qid/:title",component:AddQuestionComponent
            },
            {
                path:"update-question/:quesId",component:UpdateQuestionComponent
            }

        ],
        
        canActivate:[AdminGuard]
    },{
        path:'user',component:UserDashboardComponent,canActivate:[NoramalGuard],
        children:[
            {
                path:'user-profile',component:UserProfileComponent
            },
            {
                path:'update/:id',component:UpdateProfileComponent
            },
            {
                path:':cId',
                component:LoadQuizComponent
            },
            {
                path:'instructions/:qid',
                component:InstructionsComponent
            }
            
        ]
    }
    
    


];
