import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SampleType } from '../models/sampleType.model';


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
export class SampleTypeService {

  baseUrl = "http://localhost:8080/";
  
  constructor(private http: HttpClient) { }

  public getSampleTypes() : Observable<SampleType[]>{
    return this.http.get<SampleType[]>(this.baseUrl + 'sampleTypes', httpOptions);
  }

  public getSampleTypeById(id: number): Observable<SampleType>{
    return this.http.get<SampleType>(this.baseUrl + 'sampleTypes/' + id, httpOptions);
  }

  public addSampleType(sampleType): Observable<SampleType>{
    let body = JSON.stringify(sampleType);
    return this.http.post<SampleType>(this.baseUrl + 'sampleTypes',body, httpOptions);
  }

  public updateSampleType(sampleType): Observable<SampleType>{
    let body = JSON.stringify(sampleType);
    return this.http.patch<SampleType>(this.baseUrl + 'sampleTypes/' + sampleType.id, sampleType, httpOptions);
  }

  public deleteSampleType(id: number): Observable<SampleType>{
    return this.http.delete<SampleType>(this.baseUrl + 'sampleTypes/' + id, httpOptions);
  }
}
