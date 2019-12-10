import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Patient } from '../models/patient.model';

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
export class PatientService {

  baseUrl = "http://localhost:8080/";

  constructor(private http: HttpClient) { }

  public getPatients() {
    return this.http.get(this.baseUrl + 'patients', httpOptions);
  }

  public getPatientById(id: number){
    return this.http.get(this.baseUrl + 'patients/' + id, httpOptions);
  }

  public addPatient(patient): Observable<Patient>{
    let body = JSON.stringify(patient);
   // console.log(patient);
   //console.log(this.baseUrl+'patients');
   return  this.http.post<Patient>(this.baseUrl + 'patients/' ,body, httpOptions);

  }

  public updatePatient(patient): Observable<Patient>{
    let body = JSON.stringify(patient);
    return this.http.patch<Patient>(this.baseUrl + patient.sex.id + '/' + patient.sigle.id + '/patients' + patient.id, patient, httpOptions);
  }

  public deletePatient(id: number){
    return this.http.delete(this.baseUrl + 'patients/' + id, httpOptions);
  }
}
