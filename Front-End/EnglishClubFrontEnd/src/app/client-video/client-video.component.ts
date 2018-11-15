import { Component, OnInit, ViewEncapsulation } from '@angular/core';
declare var $: any;

@Component({
  selector: 'app-client-video',
  templateUrl: './client-video.component.html',
  styleUrls: ['./client-video.component.css']
})
export class ClientVideoComponent implements OnInit {

  constructor() { }

  ngOnInit() {
    $("#ComponentTitle").text("Videos");
  }

}
