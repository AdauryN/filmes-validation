import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const BASIC_URL = "http://localhost:8080/";

@Injectable({
  providedIn: 'root'
})
export class ReviewService {

  constructor(private http: HttpClient) { }

  giveReview(reviewDTO:any): Observable<any>{
    return this.http.post(BASIC_URL + `api/review`, reviewDTO)
  }

  getAllReviewsOfUser(id:any): Observable<any>{
    return this.http.get(BASIC_URL + `api/review/${id}`)
  }
}
