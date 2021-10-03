import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Industry} from "../model/industry";
import {Profile} from "../model/profile";

@Injectable({
  providedIn: 'root'
})
export class HttpService {

  apiURL = 'http://localhost:8080/test-task';

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }

  constructor(private http: HttpClient) { }

  fetchIndustries() {
    return this.http.get<Industry[]>(this.apiURL + '/api/industry').toPromise();
  }

  saveProfile(profile: any) {
    return this.http.post<Profile>(this.apiURL + '/api/profile', JSON.stringify(profile), this.httpOptions)
      .toPromise();
  }

  updateProfile(uuid: String, profile: any) {
    return this.http.put<Profile>(this.apiURL + '/api/profile/' + uuid, JSON.stringify(profile), this.httpOptions)
      .toPromise()
  }
}
