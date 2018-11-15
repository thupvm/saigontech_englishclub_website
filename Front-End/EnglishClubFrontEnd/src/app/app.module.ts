import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { LoginComponent } from './login/login.component';
import { AdminHomeComponent } from './admin-home/admin-home.component';
import { AdminNavBarComponent } from './admin-nav-bar/admin-nav-bar.component';
import { ClientNavBarComponent } from './client-nav-bar/client-nav-bar.component';
import { ClientHomeComponent } from './client-home/client-home.component';
import { ClientVideoComponent } from './client-video/client-video.component';
import { CookieService } from 'ngx-cookie-service';
import { ClientEMaterialComponent } from './client-e-material/client-e-material.component';
import { ClientTipComponent } from './client-tip/client-tip.component';
import { ClientNewsComponent } from './client-news/client-news.component';



@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    AdminHomeComponent,
    AdminNavBarComponent,
    ClientNavBarComponent,
    ClientHomeComponent,
    ClientVideoComponent,
    ClientEMaterialComponent,
    ClientTipComponent,
    ClientNewsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule, 
    HttpModule
  ],
  providers: [CookieService],
  bootstrap: [AppComponent]
})
export class AppModule { }
