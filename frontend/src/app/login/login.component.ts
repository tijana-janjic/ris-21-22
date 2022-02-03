import { Component, OnInit } from '@angular/core';
import { AccountService } from "../services/account.service";
import {Login} from "../model/login";
import {AuthService} from "../services/auth.service";
import {Router} from "@angular/router";

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

  constructor(private authService: AuthService) { }

  ngOnInit(): void {
  }

  login() {
    this.error = '';

    if (this.model.email != null && this.model.password != null) {
      this.error = '';

      if (this.model.email != null && this.model.password != null) {
        this.authService.login(this.model.email, this.model.password).subscribe(
          () => {
            if (this.authService.isAgentUser()) {
              this.authService.router.navigate(['/home'])
            } else {
              this.authService.router.navigate(['/tours'])
            }
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


}
