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
export class ValeurDeReferenceService {

  baseUrl = "http://localhost:8080/";
  
  constructor(private http: HttpClient) { }

  public getValeurDeReferences() {
    return this.http.get(this.baseUrl + 'references', httpOptions);
  }

  public getValeurDeReferenceById(id: number){
    return this.http.get(this.baseUrl + 'references/' + id, httpOptions);
  }

  public addValeurDeReference(valeurDeReference){
    let body = JSON.stringify(valeurDeReference);
    this.http.post(this.baseUrl + 'references',body, httpOptions);
  }

  public updateValeurDeReference(valeurDeReference){
    let body = JSON.stringify(valeurDeReference);
    this.http.patch(this.baseUrl + 'references/' + valeurDeReference.id, valeurDeReference, httpOptions);
  }

  public deleteValeurDeReference(id: number){
    return this.http.delete(this.baseUrl + 'references/' + id, httpOptions);
  }
}
