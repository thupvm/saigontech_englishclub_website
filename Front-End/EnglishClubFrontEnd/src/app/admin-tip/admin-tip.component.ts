import { Component, OnInit } from '@angular/core';
import { SecureApiService } from '../secure-api.service';
import { CookieService } from 'ngx-cookie-service';
declare var $: any;
var self: any;
var tbl: any;

var tipData: any;
var tips: any;

var getAllTipTypeURL: string;
var getAllTipTypeMethod: string;
var getAllTipURL: string;
var getAllTipMethod: string;
var deleteTipURL: string;
var deleteTipMethod: string;
var addTipURL: string;
var addTipMethod: string;
var updateTipURL: string;
var updateTipMethod: string;

@Component({
  selector: 'app-admin-tip',
  templateUrl: './admin-tip.component.html',
  styleUrls: ['./admin-tip.component.css'],
  providers: [SecureApiService, CookieService]
})
export class AdminTipComponent implements OnInit {
  tipTypes: any;
  constructor() { }

  ngOnInit() {
    $("#ComponentTitle").text("Tips Management");
    self = this;
    
  }

}
