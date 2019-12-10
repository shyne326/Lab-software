import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Employee } from '../models/employee.model';
import { Observable } from 'rxjs';


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
export class EmployeeService {


  baseUrl = "http://localhost:8080/";

  constructor(private http: HttpClient) { }

  public getEmployees(): Observable<Employee[]> {
    return this.http.get<Employee[]>(this.baseUrl + 'employees', httpOptions);
  }

  public getEmployeeById(id: number): Observable<Employee>{
    return this.http.get<Employee>(this.baseUrl + 'employees/' + id, httpOptions);
  }

  public addEmployee(employee): Observable<Employee>{
    let body = JSON.stringify(employee);
    return this.http.post<Employee>(this.baseUrl + 'employees',body, httpOptions);
  }

  public updateEmployee(employee): Observable<Employee>{
    let body = JSON.stringify(employee);
    return this.http.patch<Employee>(this.baseUrl + 'employees/' + employee.id, body, httpOptions);
  }

  public deleteEmployee(id: number): Observable<Employee>{
    return this.http.delete<Employee>(this.baseUrl + 'employees/' + id, httpOptions);
  }
}
