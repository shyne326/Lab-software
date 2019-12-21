import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Sample } from '../models/sample.model';
import { Result } from '../models/result.model';


let username = sessionStorage.getItem('username');
let password = sessionStorage.getItem('password');

const httpOptions = {
  headers : new HttpHeaders({'Content-Type': 'application/json',
                             "cache-control": "no-cache",
                              Authorization: 'Basic ' + btoa(username + ':' + password)
                            })
}


@Injectable({
  providedIn: 'root'
})
export class SampleService {
  
  baseUrl = "http://localhost:8080/";

  constructor(private http: HttpClient) { }

  public getSamples(): Observable<Sample[]> {

    return this.http.get<Sample[]>(this.baseUrl + 'samples', httpOptions);
  }

  public getSampleById(id: number): Observable<Sample>{
    return this.http.get<Sample>(this.baseUrl + 'samples/' + id, httpOptions);
  }

  public addSample(sample): Observable<Sample>{
    let body = JSON.stringify(sample);
    console.log(body);
   // return this.http.post<Sample>(this.baseUrl + 'patients/' + sample.patientId + '/samples',body, httpOptions);
   return this.http.post<Sample>(this.baseUrl + 'samples/', sample, httpOptions);
  }

  public updateSample(sample: Sample): Observable<Sample>{
    let body = JSON.stringify(sample);
    return this.http.put<Sample>(this.baseUrl + 'samples/' + sample.id, body, httpOptions);
  }

  public deleteSample(id: number): Observable<void>{
    return this.http.delete<void>(this.baseUrl + '' + id, httpOptions);
  }


  public updateResults(sampleId, results: Result[]) : Observable<Sample>{
    
    let body = JSON.stringify(results);
    return this.http.put<Sample>(this.baseUrl + 'samples/' + sampleId + '/results', body, httpOptions);
  }

}