import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import * as $ from 'jquery';

var self: any;
var username: string;
var password: string;

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {
  constructor(private router: Router) {
  }

  ngOnInit() {
    self = this;
  }

  loginListener() {

    var loginData = new FormData();
    loginData.append("username", $("#username").val());
    loginData.append("password", $("#password").val());

    $.ajax({
      type: 'POST',
      url: 'http://localhost:8080/SaigonTechEnglishClubBackEnd/manage/login',
      data: loginData,
      processData: false,
      contentType: false,
      success: function (data) {
        console.log(data);
        $("#notification").text(data.message);
        $("#username").val("");
        $("#password").val("");
      }
      ,
      error: function (data) {
        console.log(data);
      }

    });

  }
}

