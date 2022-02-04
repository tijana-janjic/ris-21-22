import { Component, OnInit } from '@angular/core';
import {Login} from "../model/login";
import {AuthService} from "../services/auth.service";
import { CookieService } from 'ngx-cookie-service';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  model : Login = {
    email : '',
    password : ''
  }
  private error: string | undefined;

  constructor(private authService: AuthService, private cookieService: CookieService) { }

  ngOnInit(): void {
  }

  login() {
    this.error = '';
    if (this.model.email != '' && this.model.password != '') {
      this.authService.login(this.model.email, this.model.password).subscribe(
        (res) => {
          console.log('logged in')
          this.authService.router.navigate(['/home'])
        },
        (err) => {
          if (err.status === 400) {
            this.error = 'Wrong email or password';
          }
        }
      );
    }
  }




}
