import { Injectable } from '@angular/core';
import { ConnectionService } from './connection.service';
var url: any;
@Injectable({
  providedIn: 'root'
})
export class SecureApiService {

  constructor(private connection: ConnectionService) { 
    url = this.connection.link;
  }

  video = {
    getAll : {method : 'GET', url : this.connection.link + 'manage/video'},
    remove : {method : 'DELETE', url : this.connection.link + 'manage/video/'},
    add : {method : 'POST', url : this.connection.link + 'manage/video'},
    update : {method : 'PUT', url : this.connection.link + 'manage/video'}
  };

  tip = {
    getAll : {method : 'GET', url : this.connection.link + 'manage/tip'},
    remove : {method : 'DELETE', url : this.connection.link + 'manage/tip/'},
    add : {method : 'POST', url : this.connection.link + 'manage/tip'},
    update : {method : 'PUT', url : this.connection.link + 'manage/tip/'}
  };

  videoType = {
    getAll : {method : 'GET', url : this.connection.link + 'videoType'}
  };

  tipType = {
    getAll : {method : 'GET', url : this.connection.link + 'tipType'}
  };
}
