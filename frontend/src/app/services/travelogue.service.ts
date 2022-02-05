import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {Travelogue} from "../model/travelogue";
import {HttpClient} from "@angular/common/http";
import {Tour} from "../model/tour";
import {CityTour} from "../model/city-tour";

@Injectable({
  providedIn: 'root'
})
export class TravelogueService {

  private url: string = "http://localhost:9000/travelagency/travel"

  constructor(
    private http: HttpClient
  ) { }

  getAllTravelogues() : Observable<Travelogue[]> {
    return this.http.get<Travelogue[]>(
      this.url + '/travelogues'
    )
  }

}
