import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ConnectionService {

  constructor() { }

  link = "http://localhost:8080/SaigonTechEnglishClubBackEnd/";

  imageLink = this.link+'getImage/';
  fileLink = this.link+'getFile/';

}
