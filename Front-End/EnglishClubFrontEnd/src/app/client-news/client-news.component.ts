import { Component, OnInit } from '@angular/core';
import { NonSecureApiService } from '../non-secure-api.service';
import { ConnectionService } from '../connection.service';
declare var $: any;
var news: any;
var self: any;
var getAllNewsURL: string;
var getAllNewsMethod: string;
var imageLink: string;


@Component({
  selector: 'app-client-news',
  templateUrl: './client-news.component.html',
  styleUrls: ['./client-news.component.css'],
  providers: [NonSecureApiService, ConnectionService]
})
export class ClientNewsComponent implements OnInit {
  newsTitle: string;
  newsContent: string;
  news: any;
  p: any;
  constructor(private nonSecureApi: NonSecureApiService, private connection: ConnectionService) { }

  ngOnInit() {
    imageLink = this.connection.imageLink;
    $("#ComponentTitle").text("News");
    self = this;
    getAllNewsURL = this.nonSecureApi.news.getAll.url;
    getAllNewsMethod = this.nonSecureApi.news.getAll.method;

    $.ajax({
      url: getAllNewsURL,
      type: getAllNewsMethod,
      success: function (data) {
        self.news = data.data;
        console.log(self.news);

      },
      error: function (data) {
        return null;
      }
    });
    

  }

  getImage(imageName: string){
    return imageLink+imageName;
  }

  openDetail(title: string, content: string){
    this.newsTitle = title;
    $("#newsContent").empty();

    $("#newsContent").append(content);
  }

}
