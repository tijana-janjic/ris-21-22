import { CityTour } from "./city-tour";
import {Byte} from "@angular/compiler/src/util";
import {FileDescription} from "./file";

export interface Tour {
  id: number
  name : string
  startDate : string
  endDate : string
  deadline : string
  price : number
  maxPassengers : number
  transportationType : string
  tourType : string
  guideId : number
  agentId : number
  description: string
  //cityTours: CityTour[]
  coverImage: FileDescription | null
}
