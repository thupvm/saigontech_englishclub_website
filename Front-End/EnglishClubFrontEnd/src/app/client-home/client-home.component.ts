import { Component, OnInit } from '@angular/core';
declare var $: any;
@Component({
  selector: 'app-client-home',
  templateUrl: './client-home.component.html',
  styleUrls: ['./client-home.component.css']
})
export class ClientHomeComponent implements OnInit {

  constructor() { }

  ngOnInit() {
    $("#ComponentTitle").text("Home");
  }

}
