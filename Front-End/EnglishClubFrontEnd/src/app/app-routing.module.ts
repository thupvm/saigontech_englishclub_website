import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { AdminNavBarComponent } from './admin-nav-bar/admin-nav-bar.component';
import { ClientNavBarComponent } from './client-nav-bar/client-nav-bar.component';
import { ClientVideoComponent } from './client-video/client-video.component';
import { ClientHomeComponent } from './client-home/client-home.component';
import { ClientNewsComponent } from './client-news/client-news.component';
import { ClientTipComponent } from './client-tip/client-tip.component';
import { ClientEMaterialComponent } from './client-e-material/client-e-material.component';

const routes: Routes = [
  
  { 
    path: 'admin/login', 
    component: LoginComponent
  },
  { path: 'manage', 
    component: AdminNavBarComponent
    
  },
  { path: 'client', 
    component: ClientNavBarComponent,
    children:[
      {
        path: 'video',
        component: ClientVideoComponent
      },
      {
        path: 'ematerial',
        component: ClientEMaterialComponent
      },
      {
        path: 'tip',
        component: ClientTipComponent
      },
      {
        path: 'news',
        component: ClientNewsComponent
      },
      {
        path: 'home',
        component: ClientHomeComponent
      },

    ]
  }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule { }
