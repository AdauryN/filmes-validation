import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserStorageService } from '../../../services/user-storage.service';

const BASIC_URL = "http://localhost:8080/";

@Injectable({
  providedIn: 'root'
})
export class EventRequestService {

  // Constructor to inject the HttpClient service for making HTTP requests
  constructor(private http: HttpClient) { }

  // Method to post a new car showroom
  createEventRequest(eventRequestDto: any): Observable<any> {
    return this.http.post(BASIC_URL + "api/event-request", eventRequestDto);
  }

  updateRequestStatus(id: any, status:any): Observable<any> {
    return this.http.get(BASIC_URL + `api/event-request/update-status/${id}/${status}`);
  }

  checkMyStatus(eventId:any): Observable<any> {
    return this.http.get(BASIC_URL + `api/event-request/check-status/${UserStorageService.getUserId()}/${eventId}`);
  }

  getApprovedRequests(id: any): Observable<any> {
    return this.http.get(BASIC_URL + `api/event-request/approved/${id}`);
  }

  getPendingOrCanceledRequests(id: any): Observable<any> {
    return this.http.get(BASIC_URL + `api/event-request/pending-or-canceled/${id}`);
  }
}
