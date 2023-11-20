import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subject, catchError, throwError } from 'rxjs';

import {
  CandidateRegistrationRequest,
  RecruiterRegistrationRequest,
} from '../models/request-model';

@Injectable({
  providedIn: 'root',
})
export class CoreService {
  baseURL: string = 'http://localhost:8080';

  private triggerMethodSubject = new Subject<void>();

  constructor(private http: HttpClient) {}

  registerCandidate(payload: CandidateRegistrationRequest): Observable<any> {
    return this.http
      .post(this.baseURL + '/api/candidates/registerCandidate', payload)
      .pipe(catchError(this.handleError));
  }

  registerRecruiter(payload: RecruiterRegistrationRequest): Observable<any> {
    return this.http
      .post(this.baseURL + '/api/recruiters/registerRecruiter', payload)
      .pipe(catchError(this.handleError));
  }

  triggerMethod() {
    this.triggerMethodSubject.next();
  }

  getMethodTrigger() {
    return this.triggerMethodSubject.asObservable();
  }

  handleError(err: HttpErrorResponse) {
    return throwError(err);
  }
}
