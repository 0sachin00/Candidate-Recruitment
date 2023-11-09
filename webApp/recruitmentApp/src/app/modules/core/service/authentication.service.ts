import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';

import { CandidateRegistrationRequest } from '../models/request-model';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  baseURL: string  = 'http://localhost:8080';
  constructor(private http: HttpClient) { }

  registerCandidate(payload: CandidateRegistrationRequest): Observable<any> {
    return this.http.post(this.baseURL + '/api/candidates/registerCandidate', payload).pipe(catchError(this.handleError));
  }

  handleError(err: HttpErrorResponse) {
    return throwError(err);
  }
}
