import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from './helper';

@Injectable({
  providedIn: 'root'
})
export class QuizService {

  constructor(private http:HttpClient) { }

  public quizzes() : any{
    return this.http.get(`${baseUrl}/quiz/`);
  }

  public addQuiz(quiz:any){
    return this.http.post(`${baseUrl}/quiz/`,quiz);
  }

  public delete(qid:any){
    return this.http.delete(`${baseUrl}/quiz/${qid}`);
  }

  public update(quiz:any){
    return this.http.put(`${baseUrl}/quiz/update`,quiz);
  }

  public getQuiz(qid:any){
    return this.http.get(`${baseUrl}/quiz/${qid}`);
  }
  public getQuizWithCategory(qid:any){
    return this.http.get(`${baseUrl}/quiz/withCategories/${qid}`);
  }

  public getQuizzesOfCategories(cId:any){
    return this.http.get(`${baseUrl}/quiz/category/${cId}`);
  }

  public getActiveQuizzes(){
    return this.http.get(`${baseUrl}/quiz/active`);
  }

  public getActiveQuizzesOfCategory(cId:any){
    return this.http.get(`${baseUrl}/quiz/category/active/${cId}`);
  }

  public attemptsQuiz(qId:any,userId:any){
    return this.http.get(`${baseUrl}/api/quizzes/${qId}/attempt/${userId}`);
  }
  public getQuizAttemptsCount(qId:any){
    return this.http.get(`${baseUrl}/api/quizzes/${qId}/attempt-count`);
  }
  public getAllAttemptsOfQuiz(qId:any){
    return this.http.get(`${baseUrl}/api/quizzes/${qId}/attempted-by`);
  }
}
