import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class BackEndConnectionService {

  constructor() { }

  getConnection(){
    return 'http://localhost:8080/SaigonTechEnglishClubBackEnd';
  }
}
