import { Component, OnInit } from '@angular/core';
import { AccountService } from "../services/account.service";
import {LoginModel} from "../model/login-model";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  model : LoginModel = {
    email : '',
    password : ''
  }
  private error: string | undefined;

  constructor(private accountService: AccountService) { }

  ngOnInit(): void {
  }

  login() {
    this.error = '';

    if (this.model.email != null && this.model.password != null) {
      this.accountService.login(this.model.email, this.model.password).subscribe(
        () => {
          console.log('ok')
        },
        (err) => {
          if (err.status === 400) {
            this.error = 'Wrong email or password';
          } else {
            this.error = 'wierd error';
          }
        }
      );
    }
  }


}
