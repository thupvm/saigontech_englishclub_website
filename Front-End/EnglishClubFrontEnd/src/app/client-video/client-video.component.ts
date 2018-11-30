import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { Http, Headers, RequestOptions, Response } from '@angular/http';
import { NonSecureApiService } from '../non-secure-api.service';

declare var $: any;
var self: any;


@Component({
  selector: 'app-client-video',
  templateUrl: './client-video.component.html',
  styleUrls: ['./client-video.component.css'],
  providers: [NonSecureApiService]
})
export class ClientVideoComponent implements OnInit {
  videos: any;
  videoDescription: string = "none";
  videoLink: string = "none";
  p: number = 1;

  constructor(private nonSecureApi: NonSecureApiService) { }

  ngOnInit() {
    self = this;
    $("#ComponentTitle").text("Videos");
    var videoTypeURL: string = this.nonSecureApi.videoType.getAll.url;
    var videoURL: string = this.nonSecureApi.video.getAll.url;

    $.ajax({
      url: videoTypeURL,
      type: "GET",
      success: function (data) {
        console.log(data);
       
      },
      error: function (data) {
        return null;
      }
    });

    $.ajax({
      url: videoURL,
      type: "GET",
      success: function (data) {
        console.log(data);
        self.videos = data.data;
      },
      error: function (data) {
        return null;
      }
    });

    $('[data-fancybox]').fancybox({
      youtube: {
        autoStart: true
      }
    });

  
  }

  getYoutubeVidID(url_string: string) {
    var url = new URL(url_string);
    var res = url.searchParams.get("v");
    return "https://img.youtube.com/vi/" + res + "/0.jpg";
  }

  openDetail(description, link) {
    this.videoDescription = description;
    this.videoLink = link;
  }


}
