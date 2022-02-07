import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {Tour} from "../../model/tour";

interface DialogData {
  tours: Tour[]
}

@Component({
  selector: 'app-my-trips',
  templateUrl: './my-trips.component.html',
  styleUrls: ['./my-trips.component.css']
})
export class MyTripsComponent implements OnInit {
  displayedColumns = ['Name', 'Starting date', 'End date', 'Price'];
  trips: Tour[];

  constructor(
    public dialogRef: MatDialogRef<MyTripsComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData,
  ) {
        this.trips = data.tours
  }

  ngOnInit(): void {
  }

  cancelReservation(id: number) {

  }
}
