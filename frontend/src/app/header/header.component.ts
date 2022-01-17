import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  logoPath: string = "assets/images/paper-plane-logo.png"
  fillerContent = [1,2,3,4,5,6]
  tripTypes = ["summer",'winter','weekend','winter','weekend','winter','weekend','winter','weekend']
  open = false
  constructor() { }

  ngOnInit(): void {

  }


}
