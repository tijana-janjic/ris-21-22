import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private readonly url = 'http://localhost:9000/travelagency/auth';

  constructor(
    private http: HttpClient,
    public router: Router
  ) {}

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
    return this.http.get(this.url + '/logout').subscribe(
      () => {this.router.navigate(['/login'])},
      (err) => {
        console.log("Login error")
      }
    )
  }

  isAgentUser(): boolean {
    let role = this.getRoleCookie();
    if (role && role === 'agent') {
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
}
