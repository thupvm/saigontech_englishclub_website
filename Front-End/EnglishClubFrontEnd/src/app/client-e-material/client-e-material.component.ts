import { Component, OnInit } from '@angular/core';
declare var $: any;
@Component({
  selector: 'app-client-e-material',
  templateUrl: './client-e-material.component.html',
  styleUrls: ['./client-e-material.component.css']
})
export class ClientEMaterialComponent implements OnInit {

  constructor() { }

  ngOnInit() {
    $("#ComponentTitle").text("e-Materials");
  }

}
