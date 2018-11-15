import { Component, OnInit } from '@angular/core';
declare var $: any;
@Component({
  selector: 'app-client-tip',
  templateUrl: './client-tip.component.html',
  styleUrls: ['./client-tip.component.css']
})
export class ClientTipComponent implements OnInit {

  constructor() { }

  ngOnInit() {
    $("#ComponentTitle").text("Tips");
  }

}
