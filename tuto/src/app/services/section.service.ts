import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Section } from '../models/section.model';


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
export class SectionService {

  baseUrl = "http://localhost:8080/";
  
  constructor(private http: HttpClient) { }

  public getSections(): Observable<Section[]> {
    return this.http.get<Section[]>(this.baseUrl + 'sections', httpOptions);
  }

  public getSectionById(id: number): Observable<Section>{
    return this.http.get<Section>(this.baseUrl + 'sections/' + id, httpOptions);
  }

  public getSectionByName(name: string): Observable<Section>{

    return this.http.get<Section>(this.baseUrl + 'sections/' + name, httpOptions);
  }

  public addSection(section): Observable<Section>{
    let body = JSON.stringify(section);
    return this.http.post<Section>(this.baseUrl + 'sections',body, httpOptions);
  }

  public updateSection(section): Observable<Section>{
    let body = JSON.stringify(section);
    return this.http.patch<Section>(this.baseUrl + 'sections/' + section.id, section, httpOptions);
  }

  public deleteSection(id: number){
    return this.http.delete(this.baseUrl + 'sections/' + id, httpOptions);
  }
}
