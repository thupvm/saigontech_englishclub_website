import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ConnectionService {

  constructor() { }
  getConnection(){
    return "http://localhost:8080/SaigonTechEnglishClubBackEnd/";
  }
}
