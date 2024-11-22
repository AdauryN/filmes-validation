import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserStorageService } from '../../../services/user-storage.service';

const BASIC_URL = "http://localhost:8080/";

@Injectable({
  providedIn: 'root'
})
export class EventService {

    // Constructor to inject the HttpClient service for making HTTP requests
    constructor(private http: HttpClient) { }

    // Method to post a new car showroom
    postEvent(eventDto: any): Observable<any> {
      return this.http.post(BASIC_URL + "api/event", eventDto);
    }

    getFutureEvents(): Observable<any> {
      return this.http.get(BASIC_URL + "api/event/all");
    }

    getFutureFriendsEvents(): Observable<any> {
      return this.http.get(BASIC_URL + `api/event/cercle/${UserStorageService.getUserId()}`);
    }

    getEvent(id:number): Observable<any> {
      return this.http.get(BASIC_URL + `api/event/${id}`);
    }
}
