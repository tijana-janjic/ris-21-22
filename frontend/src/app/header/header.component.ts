import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {HeaderService} from "../services/header.service";
import { AuthService } from 'src/app/services/auth.service';
import {CookieService} from "ngx-cookie-service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  router = Router
  logoPath: string = "assets/images/paper-plane-logo.png"
  fillerContent = [1,2,3,4,5,6]
  tripTypes = ["summer",'winter','weekend','winter','weekend','winter','weekend','winter','weekend']
  open = false

  constructor(router: Router,
              public service: HeaderService,
              private authService: AuthService) { }

  ngOnInit(): void {

  }

  getRoleFromCookie(): string | null {
    let cookies = document.cookie;
    console.log(cookies)
    for (let cookie of cookies.split(';')) {
      let cookieParts = cookie.trim().split('=');
      if (cookieParts[0] === 'role') return cookieParts[1];
    }
    return null;
  }

  getTourTypes() {
    return this.service.getTourTypes()
  }

  logout() {
    this.authService.logout()
  }

}
