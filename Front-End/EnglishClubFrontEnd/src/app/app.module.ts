import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { NgxPaginationModule } from 'ngx-pagination';
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
import { AdminVideoComponent } from './admin-video/admin-video.component';
import { AdminEMaterialComponent } from './admin-e-material/admin-e-material.component';
import { AdminTipComponent } from './admin-tip/admin-tip.component';
import { AdminNewsComponent } from './admin-news/admin-news.component';



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
    ClientNewsComponent,
    AdminVideoComponent,
    AdminEMaterialComponent,
    AdminTipComponent,
    AdminNewsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule, 
    HttpModule,
    NgxPaginationModule
  ],
  providers: [CookieService],
  bootstrap: [AppComponent]
})
export class AppModule { }
