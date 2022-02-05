import {City} from "./city";
import {FileDescription} from "./file";

export interface Travelogue {
  id: number
  title : string
  text : string;
  city: City;
  agentEmail: String
  agentName: String
  agentSurname: String
  coverImage: FileDescription
}
