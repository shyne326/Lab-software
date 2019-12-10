import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, ObservableLike } from 'rxjs';
import { Device } from '../models/device.model';


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
export class DeviceService {

  baseUrl = "http://localhost:8080/";

  constructor(private http: HttpClient) { }

  public getDevices(): Observable<Device[]> {
    return this.http.get<Device[]>(this.baseUrl + 'devices', httpOptions);
  }

  public getDeviceById(id: number): Observable<Device>{
    return this.http.get<Device>(this.baseUrl + 'devices/' + id, httpOptions);
  }

  public addDevice(device): Observable<Device>{
    let body = JSON.stringify(device);
    return this.http.post<Device>(this.baseUrl + 'devices',body, httpOptions);
  }

  public updateDevice(device):Observable<Device>{
    let body = JSON.stringify(device);
     return this.http.patch<Device>(this.baseUrl + 'devices/' + device.id, device, httpOptions);
  }

  public deleteDevice(id: number): Observable<Device>{
    return this.http.delete<Device>(this.baseUrl + 'devices/' + id, httpOptions);
  }
}
