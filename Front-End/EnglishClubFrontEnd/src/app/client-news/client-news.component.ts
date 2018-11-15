import { Component, OnInit } from '@angular/core';
declare var $: any;
@Component({
  selector: 'app-client-news',
  templateUrl: './client-news.component.html',
  styleUrls: ['./client-news.component.css']
})
export class ClientNewsComponent implements OnInit {

  constructor() { }

  ngOnInit() {
    $("#ComponentTitle").text("News");
    
  }

}
