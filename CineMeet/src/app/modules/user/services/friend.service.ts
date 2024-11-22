import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const BASIC_URL = "http://localhost:8080/";

@Injectable({
  providedIn: 'root'
})
export class FriendService {

  constructor(private http: HttpClient) { }

  sendFriendRequest(userId: any, friendId: number): Observable<string> {
    const params = new HttpParams().set('userId', userId).set('friendId', friendId);
    return this.http.post<string>(BASIC_URL + `api/friends/send-request`, {}, { params });
  }

  getFriendRequests(userId: any): Observable<any> {
    const params = new HttpParams().set('userId', userId.toString());
    return this.http.get<any>(BASIC_URL + `api/friends/requests`, { params });
  }

  getFriends(userId: any): Observable<any> {
    const params = new HttpParams().set('userId', userId.toString());
    return this.http.get<any>(BASIC_URL + `api/friends/list`, { params });
  }

  respondToFriendRequest(requestId: number, status: 'ACCEPTED' | 'REJECTED'): Observable<string> {
    const params = new HttpParams().set('requestId', requestId.toString()).set('status', status);
    return this.http.post<string>(BASIC_URL + `api/friends/respond`, {}, { params });
  }
  
}
