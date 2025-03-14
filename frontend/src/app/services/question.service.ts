import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from './helper';

@Injectable({
  providedIn: 'root'
})
export class QuestionService {
  

  constructor(private http:HttpClient) { }

  public getQuestionsOfQuiz(qid:any){
    return this.http.get(`${baseUrl}/question/quiz/all/${qid}`);
  }
  public getQuestionsOfQuizForTest(qid:any){
    return this.http.get(`${baseUrl}/question/quiz/${qid}`);
  }

  public addQuestionTOQuiz(question:any){
    return this.http.post(`${baseUrl}/question/`,question);
  }
  public delete(qid: any) {
    return this.http.delete(`${baseUrl}/question/${qid}`);
  }

  public updateQuestion(question:any){
    return this.http.put(`${baseUrl}/question/`,question);
  }

  public getQuestion(quesId:any){
    return this.http.get(`${baseUrl}/question/${quesId}`);
  }
  public evalQuiz(questions: any){
    return this.http.post(`${baseUrl}/question/eval/quiz`,questions);
  }
}
