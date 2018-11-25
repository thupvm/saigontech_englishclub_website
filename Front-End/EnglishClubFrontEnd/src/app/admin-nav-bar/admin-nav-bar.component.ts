import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { ConnectionService } from '../connection.service';
declare var $: any;
@Component({
  selector: 'app-admin-nav-bar',
  templateUrl: './admin-nav-bar.component.html',
  styleUrls: ['./admin-nav-bar.component.css'],
  providers: [CookieService, ConnectionService]
})
export class AdminNavBarComponent implements OnInit {
  fullName: string = "";
  constructor(private router: Router, private connection: ConnectionService, private cookie: CookieService) { }

  ngOnInit() {
    this.fullName = this.cookie.get("fullName"); 
  }

}
