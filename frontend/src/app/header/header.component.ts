import {Component, OnDestroy, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {HeaderService} from "../services/header.service";
import { AuthService } from 'src/app/services/auth.service';
import {CookieService} from "ngx-cookie-service";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit, OnDestroy {

  logoPath: string = "assets/images/paper-plane-logo.png"
  fillerContent = [1,2,3,4,5,6]
  tripTypes = ["summer",'winter','weekend']
  open = false

  constructor(
              private router: Router,
              public service: HeaderService,
              private authService: AuthService) { }

  ngOnInit(): void {

  }

  subscriptions : Subscription[] = [];

  ngOnDestroy(): void {
    console.log('gasim se...')
    const u = this.subscriptions.length
    let i = 0
    for (const x of this.subscriptions) {
      if (!x.closed)
        i = i + 1
      x.unsubscribe();
    }
    console.log('ukupno...' + u + ' ugasio sam '+ i)
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

  getAccount() {
    const role = this.authService.getRoleCookie()
    if ( role == null || role === '')
      this.router.navigate(['/login'])
    else
      this.router.navigate(['/account'])
  }

  filterTours(type: string) {
    this.router.navigate(["tours/"+type])
  }
}
