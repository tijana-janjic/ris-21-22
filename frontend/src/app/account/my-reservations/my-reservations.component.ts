import {Component, Inject, OnDestroy, OnInit} from '@angular/core';
import {Tour} from "../../model/tour";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {AuthService} from "../../services/auth.service";
import {Subscription} from "rxjs";

interface DialogData {
tours : Tour[]

}

@Component({
  selector: 'app-my-reservations',
  templateUrl: './my-reservations.component.html',
  styleUrls: ['./my-reservations.component.css']
})
export class MyReservationsComponent implements OnInit, OnDestroy {
  displayedColumns = ['Name', 'Starting date', 'Deadline', 'Price', 'Actions'];
  tours: Tour[];

  constructor(
    private authService: AuthService,
    public dialogRef: MatDialogRef<MyReservationsComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData,
  ) {
    this.tours = data.tours
  }

  s!: Subscription

  ngOnInit(): void {
  }

  ngOnDestroy(): void {
    this.s?.unsubscribe()
  }

  cancelReservation(id: number) {
    this.s = this.authService.cancel(id).subscribe(
      value => {
        this.dialogRef.close()
      }
    );
  }

}
