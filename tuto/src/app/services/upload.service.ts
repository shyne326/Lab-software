import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Sample } from '../models/sample.model';


let username = sessionStorage.getItem('username');
let password = sessionStorage.getItem('password');

const httpOptions = {
  headers : new HttpHeaders({//'Content-Type': 'application/json',
                             "cache-control": "no-cache",
                              Authorization: 'Basic ' + btoa(username + ':' + password)
                            })
}


@Injectable({
  providedIn: 'root'
})
export class UploadService {


  baseUrl = 'http://localhost:8080/'
  constructor(private http: HttpClient) { }


  public postFile(fileToUpload: File, sampleId, codeUtilisateurPatient: string, laborantinId): Observable<boolean> {

    const formData: FormData = new FormData();
    formData.append('avatar', fileToUpload, fileToUpload.name);
    formData.append('sampleId', sampleId);
    formData.append('codeUtilisateurPatient', codeUtilisateurPatient);
    formData.append('labTechnicianId', laborantinId)
    
    return this.http.post<boolean>(this.baseUrl + 'upload', formData, httpOptions)
}
}
