import { Byte } from "@angular/compiler/src/util";

export interface FileDescription {
  id: number
  altText: string
  data: Blob
}
