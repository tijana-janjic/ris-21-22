import {Component, OnDestroy, OnInit} from '@angular/core';
import {Travelogue} from "../model/travelogue";
import {TravelogueService} from "../services/travelogue.service";
import {CityTour} from "../model/city-tour";
import {TourDialogComponent} from "../tours/tour-dialog/tour-dialog.component";
import {TravelogueDialogComponent} from "./travelogue-dialog/travelogue-dialog.component";
import {MatDialog} from "@angular/material/dialog";
import {Subscription} from "rxjs";


@Component({
  selector: 'app-travelogues',
  templateUrl: './travelogues.component.html',
  styleUrls: ['./travelogues.component.css']
})
export class TraveloguesComponent implements OnInit, OnDestroy {
  travelogues: Travelogue[] = []
  private travelogueMap = new Map<number, Travelogue>()

  constructor(public travelogueService: TravelogueService, public dialog: MatDialog) { }

  ngOnInit(): void {
    this.getAllTravelogues()
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

  getAllTravelogues() {
    this.subscriptions.push(this.travelogueService.getAllTravelogues().subscribe(
      (response : Travelogue[] ) => {
        response.forEach( (x:Travelogue) => {
          console.log(JSON.stringify(x))
          this.travelogueMap.set(x.id, x)
        })
        this.travelogues.push(...this.travelogueMap.values())
        console.log('unutar subscribe ' + JSON.stringify(this.travelogueMap))
      }
    ))
  }

  openTravelogue(travelogue: Travelogue) {
    console.log('otvaram : ' + JSON.stringify(travelogue));
    console.log(JSON.stringify(this.travelogueMap));
    const dialogRef = this.dialog.open(TravelogueDialogComponent, {
      width: '1000px',
      data: {
        travelogue: travelogue
      },
    });

    this.subscriptions.push(dialogRef.afterClosed()
      .subscribe(result => {
        console.log('The dialog was closed');
      }));
  }
}
