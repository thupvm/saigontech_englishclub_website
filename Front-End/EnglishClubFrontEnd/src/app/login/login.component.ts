import { Component, OnInit, HostListener } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { NonSecureApiService } from '../non-secure-api.service';
declare var $: any;
var username: string;
var password: string;
var self: any;
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers: [CookieService, NonSecureApiService]
})
export class LoginComponent implements OnInit {

  constructor(private router: Router, private api: NonSecureApiService, private cookie: CookieService) {
    
  }

  @HostListener('document:keypress', ['$event'])
  handleKeyboardEvent(event: KeyboardEvent) { 
    if (event.keyCode == 13){
      this.login();
    }
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
      url: this.api.user.login.url,
      data: loginContent,
      type: this.api.user.login.method,
      processData: false,
      contentType: false,
      success: function (data) {
        console.log(data);

        if (data.errorCode == 0) {
          var dataLogin = data.data;
          self.cookie.set("adminID", dataLogin.adminID);
          self.cookie.set("fullName", dataLogin.fullName);
          self.cookie.set("accessToken", dataLogin.accessToken);
          self.router.navigate(['/manage/video']);
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
