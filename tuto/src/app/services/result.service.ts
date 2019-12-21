import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpEvent, HttpErrorResponse, HttpEventType } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
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

  public updateResults(results: Result[]): Observable<void>{
    let body = JSON.stringify(results);
    return this.http.put<void>(this.baseUrl + 'results', body, httpOptions);
  }

  public deleteResult(id: number): Observable<Result>{
    return this.http.delete<Result>(this.baseUrl + 'results/' + id, httpOptions);
  }




  public upload(data) {
    // let uploadURL = `${this.SERVER_URL}/auth/${userId}/avatar`;

    return this.http.post<any>(this.baseUrl + 'upload', data, {
      reportProgress: true,
      observe: 'events',
      headers: { Authorization: 'Basic ' + btoa(username + ':' + password) }

    }).pipe(map((event) => {

      switch (event.type) {

        case HttpEventType.UploadProgress:
          const progress = Math.round(100 * event.loaded / event.total);
          return { status: 'progress', message: progress };

        case HttpEventType.Response:
          return event.body;
        default:
          return `Unhandled event: ${event.type}`;
      }
    })
    );

  }
}
