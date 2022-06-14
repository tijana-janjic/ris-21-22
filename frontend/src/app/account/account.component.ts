import {Component, OnDestroy, OnInit} from '@angular/core';
import {AuthService} from "../services/auth.service";
import {Subscription} from "rxjs";
import {Account} from "../model/account";
import {DateFilterFn} from "@angular/material/datepicker";
import {Router} from "@angular/router";
import {MatDialog} from "@angular/material/dialog";
import {Tour} from "../model/tour";
import {TourService} from "../services/tour.service";
import {Article} from "../model/article";
import {MyTripsComponent} from "./my-trips/my-trips.component";
import {MyReservationsComponent} from "./my-reservations/my-reservations.component";
import {AgentToursComponent} from "./agent-tours/agent-tours.component";
import {AgentArticlesComponent} from "./agent-articles/agent-articles.component";
import {GuideArticlesComponent} from "./guide-articles/guide-articles.component";
import { BlogService } from '../services/blog.service';



@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit, OnDestroy {

  account!: Account;

  constructor(
    private router: Router,
    public authService: AuthService,
    public blogService: BlogService,
    public tourService: TourService,
    public dialog: MatDialog
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

  openToursByAgent() {
    this.subscriptions.push(
      this.tourService.getToursByAgent().subscribe(
        (response : Tour[] ) => {
          const dialogRef = this.dialog.open(AgentToursComponent, {
            width: '1000px',
            data: {
              tours: response
            },
          })
          this.subscriptions.push(dialogRef.afterClosed().subscribe(result => {
            console.log('The dialog was closed');
          }));
        }
      )
    )
  }

  openArticlesByAgent() {
    this.subscriptions.push(
      this.blogService.getArticlesByAgent().subscribe(
        (response : Article[] ) => {
          const dialogRef = this.dialog.open(AgentArticlesComponent, {
            width: '1000px',
            data: {
              articles: response
            },
          });
          this.subscriptions.push(dialogRef.afterClosed().subscribe(result => {
            console.log('The dialog was closed');
          }));
        }
      )
    )
  }

  openMyReservations() {
    this.subscriptions.push(
      this.tourService.getReservations().subscribe(
        (response : Tour[] ) => {
          let tours = response
          const dialogRef = this.dialog.open(MyReservationsComponent, {
            width: '1000px',
            data: {
              tours: tours
            },
          });
          this.subscriptions.push(dialogRef.afterClosed().subscribe(result => {
            console.log('The dialog was closed');
          }));
        }
      )
    )
  }

  getMonthlyReport() {
    this.getReport()
  }

  getToursByGuide() {
    this.subscriptions.push(
      this.tourService.getToursByGuide().subscribe(
        (response : Tour[] ) => {
          const dialogRef = this.dialog.open(GuideArticlesComponent, {
            width: '1000px',
            data: {
              tours: response
            },
          });
          this.subscriptions.push(dialogRef.afterClosed().subscribe(result => {
            console.log('The dialog was closed');
          }));
        }
      )
    )
  }

  openMyTrips() {
    this.subscriptions.push(
      this.tourService.getClientTrips().subscribe(
        (response : Tour[] ) => {
          console.log(response)
          const dialogRef = this.dialog.open(MyTripsComponent, {
            width: '1000px',
            data: {
              tours: response
            },
          });
          this.subscriptions.push(dialogRef.afterClosed().subscribe(result => {
            console.log('The dialog was closed');
          }));
        }
      )
    )
  }

  getReport() {
    this.tourService.getReport().subscribe(
    (data: Blob) => {
      var file = new Blob([data], { type: 'application/pdf' })
      var fileURL = URL.createObjectURL(file);

// if you want to open PDF in new tab
      window.open(fileURL); 
      var a         = document.createElement('a');
      a.href        = fileURL; 
      a.target      = '_blank';
      a.download    = 'bill.pdf';
      document.body.appendChild(a);
      a.click();
    },
    (error) => {
      console.log('getPDF error: ',error);
    }
  );
}

}
