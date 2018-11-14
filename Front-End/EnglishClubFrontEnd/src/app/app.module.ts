import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { AppRoutingModule } from './app-routing.module';
import { AdminHomeComponent } from './admin-home/admin-home.component';

import { ClientHomeComponent } from './client-home/client-home.component';
import { ClientVideoComponent } from './client-video/client-video.component';




@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    AdminHomeComponent,
    ClientHomeComponent,
    ClientVideoComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,

  ],
  providers: []
  ,
  bootstrap: [AppComponent]
})
export class AppModule { }
