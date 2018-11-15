import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { ConnectionService } from '../connection.service';
declare var $: any;
var username: string;
var password: string;
var self: any;
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers: [CookieService, ConnectionService]
})
export class LoginComponent implements OnInit {

  constructor(private router: Router, private connection: ConnectionService, private cookie: CookieService) {

  }

  ngOnInit() {
    self = this;
  }

  login() {

    username = $("#username").val();
    password = $("#password").val();
    var loginContent = new FormData();
    loginContent.append("username", username);
    loginContent.append("password", password);

    $.ajax({
      url: this.connection.getConnection() + "manage/login",
      data: loginContent,
      type: "POST",
      processData: false,
      contentType: false,
      success: function (data) {
        console.log(data);

        if (data.errorCode == 0) {
          var dataLogin = data.data;
          self.cookie.set("adminID", dataLogin.adminID);
          self.cookie.set("fullName", dataLogin.fullName);
          self.cookie.set("accessToken", dataLogin.accessToken);
          self.router.navigate(['/client/video']);
        } else {
          $("#errorMessage").text(data.message);
        }
      },
      error: function (data) {
        console.log(data);
      }
    });

  }

}
