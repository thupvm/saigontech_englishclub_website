import { Component, OnInit } from '@angular/core';
import { NonSecureApiService } from '../non-secure-api.service';
import { ConnectionService } from '../connection.service';
declare var $: any;
var tips: any;
var self: any;
var getAllTipURL: string;
var getAllTipMethod: string;
var imageLink: string;


@Component({
  selector: 'app-client-tip',
  templateUrl: './client-tip.component.html',
  styleUrls: ['./client-tip.component.css'],
  providers: [NonSecureApiService, ConnectionService]
})
export class ClientTipComponent implements OnInit {
  tipTitle: string;
  tipContent: string;
  tips: any;
  p: any;
  constructor(private nonSecureApi: NonSecureApiService, private connection: ConnectionService) { }

  ngOnInit() {
    imageLink = this.connection.imageLink;
    $("#ComponentTitle").text("Tips");
    self = this;
    getAllTipURL = this.nonSecureApi.tip.getAll.url;
    getAllTipMethod = this.nonSecureApi.tip.getAll.method;

    $.ajax({
      url: getAllTipURL,
      type: getAllTipMethod,
      success: function (data) {
        self.tips = data.data;
        console.log(self.tips);

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
    this.tipTitle = title;
    $("#tipContent").empty();

    $("#tipContent").append(content);
  }

}
