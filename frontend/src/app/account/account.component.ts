import {Component, OnDestroy, OnInit} from '@angular/core';
import {AuthService} from "../services/auth.service";
import {Subscription} from "rxjs";
import {Account} from "../model/account";
import {DateFilterFn} from "@angular/material/datepicker";
import {Router} from "@angular/router";

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit, OnDestroy {

  account!: Account;

  constructor(
    private router: Router,
    public authService: AuthService
  ) { }

  ngOnInit(): void {
    this.subscriptions.push(this.authService.getAccount().subscribe(
      (acc) =>
        this.account = acc
      ))
  }

  subscriptions : Subscription[] = [];
  password2: string = '';

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

  isClientUser() { return this.authService.isClientUser() }
  isAgentUser() { return this.authService.isAgentUser() }
  isGuideUser() { return this.authService.isGuideUser() }

  update() {
    this.subscriptions.push(this.authService.update(this.account).subscribe(
      (acc) => {
        this.router.navigate(['account'])
      }
    ))
    //
  }

  getMinDate() {
    const minDate = new Date();
    minDate.setFullYear(minDate.getFullYear()-18, minDate.getMonth(), minDate.getDate())
    return minDate
  }

  filterYounger: DateFilterFn<Date | null> = (date: Date | null) => {
    return date != null && date <= this.getMinDate();
  };
}
