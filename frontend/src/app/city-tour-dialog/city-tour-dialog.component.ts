import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {CityTour} from "../model/city-tour";

@Component({
  selector: 'app-city-tour-dialog',
  templateUrl: './city-tour-dialog.component.html',
  styleUrls: ['./city-tour-dialog.component.css']
})
export class CityTourDialogComponent implements OnInit {

  constructor(
    public dialogRef: MatDialogRef<CityTourDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: CityTour,
  ) {}

  ngOnInit(): void {
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

}
