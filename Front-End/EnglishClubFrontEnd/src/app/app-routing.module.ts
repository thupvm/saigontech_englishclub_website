import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { LoginComponent } from './login/login.component'
import { AdminHomeComponent } from './admin-home/admin-home.component'
import { ClientHomeComponent } from './client-home/client-home.component';
import { ClientVideoComponent } from './client-video/client-video.component';
 
const routes: Routes = [
  { 
    path: 'manage/login', 
    component: LoginComponent 
  },
  { 
    path: 'client', 
    component: ClientHomeComponent, 
    children:[
      {
        path: 'video',
        component: ClientVideoComponent
      }
    ]
  }
];
 
@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}