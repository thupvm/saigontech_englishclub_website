import { Injectable } from '@angular/core';
import { ConnectionService } from './connection.service';
var url: any;
@Injectable({
  providedIn: 'root'
})
export class NonSecureApiService {
  

  constructor(private connection: ConnectionService) {
    url = this.connection.link;
  }

  user = {
    login : {method : 'POST', url : this.connection.link + 'manage/login'}
  };

  video = {
    getAll : {method : 'GET', url : this.connection.link + 'video'}
  };

  videoType = {
    getAll : {method : 'GET', url : this.connection.link + 'videoType'}
  };

  tip = {
    getAll: {method : 'GET', url : this.connection.link + 'tip'}
  }

  material = {
    getAll: {method : 'GET', url : this.connection.link + 'material'}
  }

  file = {
    getAllByMaterialID: {method : 'GET', url : this.connection.link + 'file/material/'}
  }

}
