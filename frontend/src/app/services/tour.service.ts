import { Injectable } from '@angular/core';
import {Account} from "../model/account";
import {HttpClient} from "@angular/common/http";
import {Tour} from "../model/tour";
import {Observable} from "rxjs";
import {CityTour} from "../model/city-tour";

@Injectable({
  providedIn: 'root'
})
export class TourService {

  private url: string = "http://localhost:9000/travelagency/travel"

  constructor(
    private http: HttpClient
  ) { }

  getAllTours() : Observable<Tour[]> {
    return this.http.get<Tour[]>(this.url + '/tours')
  }
  getCityToursFor(id: number) : Observable<CityTour[]>{
    return this.http.get<CityTour[]>(this.url + '/tour/city-tours?id='+id)
  }
}
