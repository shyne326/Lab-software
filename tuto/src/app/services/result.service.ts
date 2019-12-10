import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Result } from '../models/result.model';


let username = sessionStorage.getItem('username');
let password = sessionStorage.getItem('password');

const httpOptions = {
  headers : new HttpHeaders({'Content-Type': 'application/json',
                              Authorization: 'Basic ' + btoa(username + ':' + password)
                            })
}

@Injectable({
  providedIn: 'root'
})
export class ResultService {

  private baseUrl = "http://localhost:8080/";

  constructor(private http: HttpClient) { }

  public getResults(): Observable<Result[]> {
    return this.http.get<Result[]>(this.baseUrl + 'results', httpOptions);
  }

  public getResultById(id: number): Observable<Result>{
    return this.http.get<Result>(this.baseUrl + 'results/' + id, httpOptions);
  }

  public addResult(result: Result[]): Observable<Result>{
    let body = JSON.stringify(result);
    console.log(body);
    return this.http.post<Result>(this.baseUrl + 'results',body, httpOptions);
  }

  public updateResult(result): Observable<Result>{
    let body = JSON.stringify(result);
    return this.http.patch<Result>(this.baseUrl + 'results/' + result.id, result, httpOptions);
  }

  public deleteResult(id: number): Observable<Result>{
    return this.http.delete<Result>(this.baseUrl + 'results/' + id, httpOptions);
  }
}
