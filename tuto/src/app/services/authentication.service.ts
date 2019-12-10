import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Employee } from '../models/employee.model';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(private http: HttpClient) { }

  authenticate(username: string, password: string) {
   
   let validationUrl = 'http://localhost:8080/validate-user/'+username;

   const headers = new HttpHeaders({ Authorization: 'Basic ' + window.btoa(username +':' + password)});

    return this.http.get<Employee>('http://localhost:8080/validate-user/'+username, {headers}).pipe(
      map(
        (response) => {

          sessionStorage.setItem('username', username);
          sessionStorage.setItem('userRole', response.roles);
          sessionStorage.setItem('password', response.password);
          sessionStorage.setItem('userId', response.id+'');
        return response;
        }
      )
    );
  


  }
   

  isUserLoggedIn() {
    let user = sessionStorage.getItem('username');
    console.log(!(user === null));
    return !(user === null)
  }

  logOut() {
    sessionStorage.removeItem('username')
  }
}


