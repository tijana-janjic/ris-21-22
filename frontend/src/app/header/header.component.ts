import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {HeaderService} from "../services/header.service";

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

  constructor(router: Router, public service: HeaderService ) { }

  ngOnInit(): void {

  }

  getTourTypes() {
    return this.service.getTourTypes()
  }
}
