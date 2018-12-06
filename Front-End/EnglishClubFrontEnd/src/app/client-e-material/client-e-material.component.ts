import { Component, OnInit } from '@angular/core';
import { NonSecureApiService } from '../non-secure-api.service';
import { ConnectionService } from '../connection.service';
declare var $: any;
var materials: any;
var self: any;
var getAllMaterialURL: string;
var getAllMaterialMethod: string;
var imageLink: string;

var getAllFileByMaterialIDURL: string;
var getAllFileByMaterialIDMethod: string;
var fileLink: string;

var files: any;

@Component({
  selector: 'app-client-e-material',
  templateUrl: './client-e-material.component.html',
  styleUrls: ['./client-e-material.component.css'],
  providers: [NonSecureApiService, ConnectionService]
})
export class ClientEMaterialComponent implements OnInit {
  materialTitle: string;
  materialContent: string;

  constructor(private nonSecureApi: NonSecureApiService, private connection: ConnectionService) { }

  ngOnInit() {
    $("#ComponentTitle").text("e-Materials");
    imageLink = this.connection.imageLink;

    self = this;
    getAllMaterialURL = this.nonSecureApi.material.getAll.url;
    getAllMaterialMethod = this.nonSecureApi.material.getAll.method;
    getAllFileByMaterialIDURL = this.nonSecureApi.file.getAllByMaterialID.url;
    getAllFileByMaterialIDMethod = this.nonSecureApi.file.getAllByMaterialID.method;
    fileLink =this.connection.fileLink;
    
    $.ajax({
      url: getAllMaterialURL,
      type: getAllMaterialMethod,
      success: function (data) {
        self.materials = data.data;
        console.log(self.materials);

      },
      error: function (data) {
        return null;
      }
    });

    
    
  }

  getImage(imageName: string){
    return imageLink+imageName;
  }

  openDetail(title: string, content: string, materialID: number){
    // alert(materialID);
    this.materialTitle = title;
    $("#materialContent").empty();

    $("#materialContent").append(content);
    $("#materialContent").append("<br /><br /><br /><p>File List: </p>");

    $.ajax({
      url: getAllFileByMaterialIDURL+materialID,
      type: getAllFileByMaterialIDMethod,
      success: function (data) {
        self.files = data.data;
        for (var i = 0; i < self.files.length; i++){
          $("#materialContent").append("<a href='"+fileLink+self.files[i].name+"'>"+self.files[i].name+"<a><br/>");
        }
      },
      error: function (data) {
        return null;
      }
    });
  }

}
