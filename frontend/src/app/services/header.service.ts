import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class HeaderService {

  tourTypes: string[]

  constructor() {
    this.tourTypes = this.initTourTypes()
  }

  initTourTypes() {
    return [
      "Summer",
      "Winter",
      "Weekend"
    ]
  }

  getTourTypes() {
    return this.tourTypes
  }

  getAllTourTypes() {
    return this.tourTypes
  }

}
