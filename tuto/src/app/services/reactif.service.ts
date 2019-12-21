import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, ObservableLike } from 'rxjs';
import { Reactif } from '../models/reactif.model';


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
export class ReactifService {

  baseUrl = "http://localhost:8080/";

  constructor(private http: HttpClient) { }

  public getReactifs(): Observable<Reactif[]> {
    return this.http.get<Reactif[]>(this.baseUrl + 'reactifs', httpOptions);
  }

  public getReactifById(id: number): Observable<Reactif>{
    return this.http.get<Reactif>(this.baseUrl + 'reactifs/' + id, httpOptions);
  }

  public addReactif(reactif): Observable<Reactif>{
    let body = JSON.stringify(reactif);
    return this.http.post<Reactif>(this.baseUrl + 'reactifs',body, httpOptions);
  }

  public updateReactif(reactif):Observable<Reactif>{
    let body = JSON.stringify(reactif);
     return this.http.patch<Reactif>(this.baseUrl + 'reactifs/' + reactif.id, reactif, httpOptions);
  }

  public deleteReactif(id: number): Observable<Reactif>{
    return this.http.delete<Reactif>(this.baseUrl + 'reactifs/' + id, httpOptions);
  }
}
