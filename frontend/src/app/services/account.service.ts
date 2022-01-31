import { Injectable } from '@angular/core';
import { Account } from "../model/account";
import {HttpClient, HttpErrorResponse, HttpHeaders} from "@angular/common/http";
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';

const httpOptions = {
  headers: new HttpHeaders()
}

httpOptions.headers.append('Access-Control-Allow-Headers', '*');
httpOptions.headers.append('Access-Control-Allow-Origin', '*');
httpOptions.headers.append('Access-Control-Allow-Methods', 'GET, POST, PUT, OPTIONS, DETELE');
httpOptions.headers.append('Content-Type', 'application/json');
httpOptions.headers.append('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8');

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  private url: string = "http://localhost:9000/travelagency/account"
  private account: Account | null = null

  constructor(
    private http: HttpClient
  ) { }

  login(email: string, password: string) {
    var hi =
      this.http.post(
      this.url + '/login?email='+ email +'&password='+password, {}, httpOptions );
    return hi
  }

  register() {

  }

  private handleError(error: HttpErrorResponse) : Observable<Account> {
    if (error.status === 0) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', error.error);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong.
      console.error(
        `Backend returned code ${error.status}, body was: `, error.error);
    }
    // Return an observable with a user-facing error message.
    return throwError(
      'Something bad happened; please try again later.');
  }

}
