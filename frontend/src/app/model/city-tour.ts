import { City } from "./city";
import { Landmark } from "./landmark";
import { Hotel } from "./hotel";
import {Byte} from "@angular/compiler/src/util";
import {FileDescription} from "./file";

export interface CityTour {
  id : number
  city: City
  hotel : Hotel
  landmarks : Landmark[]
  facultyLandmarks  : Landmark[]
  coverImage: FileDescription
}
