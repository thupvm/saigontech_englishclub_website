import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { LoginComponent } from './login/login.component';
import { AdminHomeComponent } from './admin-home/admin-home.component';
import { AdminNavBarComponent } from './admin-nav-bar/admin-nav-bar.component';
import { ClientNavBarComponent } from './client-nav-bar/client-nav-bar.component';
import { ClientHomeComponent } from './client-home/client-home.component';
import { ClientVideoComponent } from './client-video/client-video.component';



@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    AdminHomeComponent,
    AdminNavBarComponent,
    ClientNavBarComponent,
    ClientHomeComponent,
    ClientVideoComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
