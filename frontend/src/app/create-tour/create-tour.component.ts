import { Component, OnInit } from '@angular/core';
import {Account} from "../model/account";
import {Tour} from "../model/tour";
import {DateFilterFn} from "@angular/material/datepicker";
import {Byte} from "@angular/compiler/src/util";

@Component({
  selector: 'app-create-tour',
  templateUrl: './create-tour.component.html',
  styleUrls: ['./create-tour.component.css']
})
export class CreateTourComponent implements OnInit {

  model: Tour = {
    id : -1,
    name : '',
    startDate : '',
    endDate : '',
    deadline : '',
    price : 0,
    maxPassengers : -1,
    transportationType : '',
    tourType : '',
    guideId : -1,
    agentId : -1,
    description : '',
    // cityTours : [],
    coverImage: null
  }

  constructor() { }

  ngOnInit(): void {
  }

  getMinDate() {
    return new Date();
  }

  filterOlder: DateFilterFn<Date | null> = (date: Date | null) => {
    return date != null && date >= this.getMinDate();
  };
}
