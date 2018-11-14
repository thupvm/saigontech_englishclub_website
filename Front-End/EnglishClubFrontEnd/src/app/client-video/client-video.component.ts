import { Component, OnInit } from '@angular/core';
import * as $ from 'jquery';
@Component({
  selector: 'app-client-video',
  templateUrl: './client-video.component.html',
  styleUrls: ['./client-video.component.css']
})
export class ClientVideoComponent implements OnInit {

  constructor() { }

  ngOnInit() {
    $(".ecVideo").fancyBox();
  }

}
