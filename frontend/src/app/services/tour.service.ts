import { Injectable } from '@angular/core';
import {Account} from "../model/account";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Tour} from "../model/tour";
import {Observable} from "rxjs";
import {CityTour} from "../model/city-tour";
import {NewTour} from "../create-tour/create-tour.component";
import {Landmark} from "../model/landmark";
import {Hotel} from "../model/hotel";
import {Country} from "../model/country";
import {City} from "../model/city";
import {NewCityTour} from "../create-city-tour/create-city-tour.component";
import {Article} from "../model/article";
import { AuthService } from './auth.service';

const httpOptions = {
  headers: new HttpHeaders(),
  withCredentials: true
}

const reportHeader = {
    responseType: 'blob' as 'json'
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
    private http: HttpClient,
    private authService: AuthService
  ) { }

  // city tours

  getCityTours() : Observable<CityTour[]>{
    return this.http.get<CityTour[]>(this.url + '/city-tours')
  }

  getCityToursOfTour(id: number) : Observable<CityTour[]>{
    return this.http.get<CityTour[]>(this.url + '/tour/city-tours?id='+id)
  }

  getCityToursOfCity(id: number) : Observable<CityTour[]>{
    return this.http.get<CityTour[]>(this.url + '/city/city-tours?id='+id)
  }

  saveCityTour(model: NewCityTour) {
    return this.http.post<CityTour>(this.url + '/create-city-tour', model)
  }

  deleteTour(id: number) {
    return this.http.delete(this.url + '/delete-tour?id='+id)
  }


  // tours

  getAllTours() : Observable<Tour[]> {
    return this.http.get<Tour[]>(
      this.url + '/tours',
      httpOptions
    )
  }

  saveTour(model: NewTour, cityTours: number[]) {
    return this.http.post<Tour>(this.url + '/create-tour',
      {
        ...model,
        cityTours: cityTours
      })
  }

  // other data

  getLandmarks(cityId: number) {
    return this.http.get<Landmark[]>(this.url + '/landmarks?id='+cityId)
  }

  getHotels(cityId: number) {
    return this.http.get<Hotel[]>(this.url + '/hotels?id='+cityId)
  }

  getCountries() {
    return this.http.get<Country[]>(this.url + '/countries')
  }

  getCitiesOfCountry(countryId: number) {
    return this.http.get<City[]>(this.url + '/cities?id='+countryId)
  }

  // by agent
  
  getToursByAgent() {
    return this.http.get<Tour[]>(this.url + '/agent/tours')
  }

  getReport() {
    let user = this.authService.getRoleCookie()
    return this.http.get<any>(this.url + '/'+ user +'/monthly-report', reportHeader  )
  }

  // by guide

  getToursByGuide() {
    return this.http.get<Tour[]>(this.url + '/guide/tours')
  }

  getGuideReport() {
    return this.http.get<any>(this.url + '/agent/tours-report', reportHeader  )
  }
  // by client

  getReservations() {
    return this.http.get<Tour[]>(this.url + '/client/reservations')
  }

  getClientTrips() {
    return this.http.get<Tour[]>(this.url + '/client/trips')
  }

}
