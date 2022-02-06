import {City} from "./city";
import {FileDescription} from "./file";

export interface Article {
  id: number
  title : string
  text : string;
  city: City;
  agentEmail: String
  agentName: String
  agentSurname: String
  coverImage: FileDescription
}
