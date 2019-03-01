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
    update : {method : 'POST', url : this.connection.link + 'manage/tip/'}
  };

  ematerial = {
    getAll : {method : 'GET', url : this.connection.link + 'manage/material'},
    remove : {method : 'DELETE', url : this.connection.link + 'manage/material/'},
    add : {method : 'POST', url : this.connection.link + 'manage/materialandfile'},
    update : {method : 'POST', url : this.connection.link + 'manage/material/'}
  };

  news = {
    getAll : {method : 'GET', url : this.connection.link + 'news'},
    remove : {method : 'DELETE', url : this.connection.link + 'manage/news/'},
    add : {method : 'POST', url : this.connection.link + 'manage/news'},
    update : {method : 'POST', url : this.connection.link + 'manage/news'}
  };

  tipType = {
    getAll : {method : 'GET', url : this.connection.link + 'tipType'}
  };

  ematerialType = {
    getAll : {method : 'GET', url : this.connection.link + 'materialType'},
  };

  newsType = {
    getAll : {method : 'GET', url : this.connection.link + 'newsType'},
  };


  file = {
    getAll: {method : 'GET', url : this.connection.link + 'manage/file/material/'},
    remove: {method : 'DELETE', url : this.connection.link + 'manage/file/'},
    add: {method : 'POST', url : this.connection.link + 'manage/file'}
  }

  ematerialTypes = {
    getAll : {method : 'GET', url : this.connection.link + 'materialType'},
    getAllActive : {method : 'GET', url : this.connection.link + 'active/materialType'},
    remove : {method : 'DELETE', url : this.connection.link + 'manage/materialType/'},
    add : {method : 'POST', url : this.connection.link + 'manage/materialType'},
    update : {method : 'PUT', url : this.connection.link + 'manage/materialType'}
  };

  tipTypes = {
    getAll : {method : 'GET', url : this.connection.link + 'tipType'},
    getAllActive : {method : 'GET', url : this.connection.link + 'active/tipType'},
    remove : {method : 'DELETE', url : this.connection.link + 'manage/tipType/'},
    add : {method : 'POST', url : this.connection.link + 'manage/tipType'},
    update : {method : 'PUT', url : this.connection.link + 'manage/tipType'}
  };

  newsTypes = {
    getAll : {method : 'GET', url : this.connection.link + 'newsType'},
    remove : {method : 'DELETE', url : this.connection.link + 'manage/newsType/'},
    add : {method : 'POST', url : this.connection.link + 'manage/newsType'},
    update : {method : 'PUT', url : this.connection.link + 'manage/newsType'}
  };

  videoTypes = {
    getAll : {method : 'GET', url : this.connection.link + 'videoType'},
    getAllActive : {method : 'GET', url : this.connection.link + 'active/videoType'},
    remove : {method : 'DELETE', url : this.connection.link + 'manage/videoType/'},
    add : {method : 'POST', url : this.connection.link + 'manage/videoType'},
    update : {method : 'PUT', url : this.connection.link + 'manage/videoType'}
  };

}
