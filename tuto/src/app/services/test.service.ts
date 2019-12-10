import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';


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
export class TestService {

  baseUrl = "http://localhost:8080/";
  
  constructor(private http: HttpClient) { }

  public getTests() {
    return this.http.get(this.baseUrl + 'tests', httpOptions);
  }

  public getTestById(id: number){
    return this.http.get(this.baseUrl + 'tests/' + id, httpOptions);
  }

  public addTest(test){
    let body = JSON.stringify(test);
    this.http.post(this.baseUrl + 'tests',body, httpOptions);
  }

  public updateTest(test){
    let body = JSON.stringify(test);
    this.http.patch(this.baseUrl + 'tests/' + test.id, test, httpOptions);
  }

  public deleteTest(id: number){
    return this.http.delete(this.baseUrl + 'tests/' + id, httpOptions);
  }
}
