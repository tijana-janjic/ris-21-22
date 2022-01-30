import { Component, OnInit } from '@angular/core';
import { DateFilterFn } from '@angular/material/datepicker';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {


  constructor() { }

  ngOnInit(): void {
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
