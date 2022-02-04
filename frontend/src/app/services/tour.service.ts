import { Injectable } from '@angular/core';
import {Account} from "../model/account";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Tour} from "../model/tour";
import {Observable} from "rxjs";
import {CityTour} from "../model/city-tour";

const httpOptions = {
  headers: new HttpHeaders(),
  withCredentials: true
}

httpOptions.headers.append('Access-Control-Allow-Headers', '*');
httpOptions.headers.append('Access-Control-Allow-Credentials', 'true');
httpOptions.headers.append('Access-Control-Allow-Origin', '*');
httpOptions.headers.append('Access-Control-Allow-Methods', 'GET, POST, PUT, OPTIONS, DETELE');
httpOptions.headers.append('Content-Type', 'application/json');
httpOptions.headers.append('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8');


@Injectable({
  providedIn: 'root'
})
export class TourService {

  private url: string = "http://localhost:9000/travelagency/travel"

  constructor(
    private http: HttpClient
  ) { }

  getAllTours() : Observable<Tour[]> {
    return this.http.get<Tour[]>(
      this.url + '/tours',
      httpOptions
    )
  }
  getCityToursFor(id: number) : Observable<CityTour[]>{
    return this.http.get<CityTour[]>(this.url + '/tour/city-tours?id='+id)
  }
}
