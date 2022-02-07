import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from "ngx-cookie-service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {Account} from "../model/account";
import {Observable} from "rxjs";


const httpOptions = {
  headers: new HttpHeaders()
}

httpOptions.headers.append('Access-Control-Allow-Headers', '*');
httpOptions.headers.append('Access-Control-Allow-Credentials', 'true');
httpOptions.headers.append('Access-Control-Allow-Origin', '*');
httpOptions.headers.append('Access-Control-Allow-Methods', 'GET, POST, PUT, OPTIONS, DELETE');
httpOptions.headers.append('Content-Type', 'application/json');
httpOptions.headers.append('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8');



@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private readonly url = 'http://localhost:9000/travelagency/auth';

  constructor(
    private http: HttpClient,
    public router: Router,
    public snackBar: MatSnackBar
  ) {}


  register(acc: Account) {
    return this.http.post(
      this.url + '/register',
      acc
    );
  }

  update(acc: Account) {
    return this.http.put(
      this.url + '/update',
      acc
    );
  }


  login(email: string, password: string) {
    return this.http.post(
      this.url + '/login',
      {
        email: email,
        password: password,
      }
    );
  }

  logout(){
    this.http.get(this.url + '/logout').subscribe(
      (next:Object) => {
        this.router.navigate(['/login'])
      },
      (err) => {
        this.snackBar.open(err.message, 'Close', {
          duration: 3000,
          horizontalPosition: 'end',
          verticalPosition: 'top',
          panelClass: ['snackbar'],
        });
      }
    )
  }

  getAccount(){
    return this.http.get<Account>(this.url + '/account')
  }

  isAgentUser(): boolean {
    let role = this.getRoleCookie();
    if (role && role === 'agent') {
      return true;
    }
    return false;
  }

  isGuideUser(): boolean {
    let role = this.getRoleCookie();
    if (role && role === 'guide') {
      return true;
    }
    return false;
  }

  isClientUser(): boolean {
    let role = this.getRoleCookie();
    if (role && role === 'client') {
      return true;
    }
    return false;
  }

  getRoleCookie(): string | null {
    let cookies = document.cookie;
    for (let cookie of cookies.split(';')) {
      let cookieParts = cookie.trim().split('=');
      if (cookieParts[0] === 'role') return cookieParts[1];
    }
    return null;
  }

  getAllGuides() : Observable<Account[]>{
    return this.http.get<Account[]>(this.url+"/guides")
  }

  reserve(tourId: number){
    return this.http.put(
      this.url + '/reserve?id='+tourId,
      {})
  }


  cancel(tourId: number) {
    return this.http.put(
      this.url + '/cancel?id='+tourId,
      {})
  }

}
