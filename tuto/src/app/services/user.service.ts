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
export class UserService {

  baseUrl = "http://localhost:8080/";
  
  constructor(private http: HttpClient) { }

  public getUsers() {
    return this.http.get(this.baseUrl + 'users', httpOptions);
  }

  public getUserById(id: number){
    return this.http.get(this.baseUrl + 'users/' + id, httpOptions);
  }

  public addUser(user){
    let body = JSON.stringify(user);
    this.http.post(this.baseUrl + 'users',body, httpOptions);
  }

  public updateUser(user){
    let body = JSON.stringify(user);
    this.http.patch(this.baseUrl + 'users/${user.id}', user, httpOptions); // Will test and do this for all the others
  }

  public deleteUser(id: number){
    return this.http.delete(this.baseUrl + 'users/' + id, httpOptions);
  }
}
