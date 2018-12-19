import { Component, OnInit } from '@angular/core';
import { SecureApiService } from '../secure-api.service';
import { CookieService } from 'ngx-cookie-service';
import { ConnectionService } from '../connection.service';
declare var $: any;
var self: any;
var tbl: any;

var videoTypesData: any;
var videoTypes: any;

var getAllVideoTypesURL: string;
var getAllVideoTypesMethod: string;

var addVideoTypesURL: string;
var addVideoTypesMethod: string;

var updateVideoTypesURL: string;
var updateVideoTypesMethod: string;

var deleteVideoTypesURL: string;
var deleteVideoTypesMethod: string;

var rowId: number;

@Component({
  selector: 'app-admin-video-type',
  templateUrl: './admin-video-type.component.html',
  styleUrls: ['./admin-video-type.component.css'],
  providers: [SecureApiService, CookieService, ConnectionService]
})
export class AdminVideoTypeComponent implements OnInit {

  constructor(private connection: ConnectionService, private secureApi: SecureApiService, private cookie: CookieService) { }

  ngOnInit() {

    $("#ComponentTitle").text("Video Types Management");

    self = this;

    getAllVideoTypesURL = this.secureApi.videoTypes.getAll.url;
    getAllVideoTypesMethod = this.secureApi.videoTypes.getAll.method;

    addVideoTypesURL = this.secureApi.videoTypes.add.url;
    addVideoTypesMethod = this.secureApi.videoTypes.add.method;

    updateVideoTypesURL = this.secureApi.videoTypes.update.url;
    updateVideoTypesMethod = this.secureApi.videoTypes.update.method;

    deleteVideoTypesURL = this.secureApi.videoTypes.remove.url;
    deleteVideoTypesMethod = this.secureApi.videoTypes.remove.method;

  tbl = $("#tbl").DataTable({
    columnDefs: [
      { orderable: false, targets: [0, 1] }
    ],
    aLengthMenu: [
      [10, 25, 50, 100, -1],
      [10, 25, 50, 100, "All"]
    ],
    iDisplayLength: 50,
    aaData: null,
    rowId: "id",
    columns: [
      {
        data: null, className: "text-center", render: function (data, type, row, meta) {
          return meta.row + meta.settings._iDisplayStart + 1;
        }
      },
      { data: "name" },
      { data: "status", render: function (data, type, row) {
          return self.getTitle(data)
      } },
      {
        data: null, className: "text-right", render: function (data, type, row) {
          return '<i data-group="grpEdit" class="fa fa-edit text-success pointer"></i> ' +
            '<i data-group="grpDelete" class="fa fa-remove text-danger pointer"></i>';
        }
      }
    ],
    initComplete: function (settings, json) {
      self.loadTable();

    },
    drawCallback: function (settings) {
      self.bindTableEvents();
    }
  });


  $("#popup").modal({ show: false }).on('show.bs.modal', function () {
    var id = $("#hidId").val();
    if (id == 0) { //add
      $("#txtName").val("");
    } else {
     
      $("#txtName").val(videoTypesData.name);
      $("#typeStatus").val(videoTypesData.status+"");
    }
  });

  $("#btnAdd").click(function () {
    $("#hidId").val(0);
    $("#popup").modal('show');
    $("#statusForm").hide();
  });

  $('textarea#txtContent').froalaEditor({
    heightMin: 500,
    heightMax: 500,
    toolbarButtons: ['bold', 'italic', 'underline', 'strikeThrough', 'subscript', 'superscript', '|', 'fontFamily', 'fontSize', 'color', 'inlineStyle', 'inlineClass', 'clearFormatting', '|', 'emoticons', 'fontAwesome', 'specialCharacters', '-', 'paragraphFormat', 'lineHeight', 'paragraphStyle', 'align', 'formatOL', 'formatUL', 'outdent', 'indent', 'quote', '|', 'insertLink', 'insertImage', 'insertVideo', 'insertFile', 'insertTable', '-', 'insertHR', 'selectAll', 'getPDF', 'print', 'help', 'html', 'fullscreen', '|', 'undo', 'redo']
  });

  $("#btnSave").click(function(){
    var id = $("#hidId").val();
    if (id == 0) {
    
      var addData = {
        "name":$("#txtName").val(),
        "status": true
      }
      $.ajax({
        url: addVideoTypesURL,
        data: JSON.stringify(addData),
        type: "POST",
        contentType: "application/json",
        success: function (data) {
          if (data.errorCode == 0) {
            self.loadTable();
            $("#popup").modal('hide');
            $.alert('This Type has been added!');

          } else {
            $.alert(data.message);
          }
        },
        error: function (data) {
          console.log(data);
        }
      });
    } else {

     

      var updateData = {
        "id":videoTypesData.id,
        "name":$("#txtName").val(),
        "status": $("#typeStatus").val()
      }

      $.ajax({
        url: updateVideoTypesURL,
        data: JSON.stringify(updateData),
        type: updateVideoTypesMethod,
        contentType: "application/json",
        success: function (data) {
          if (data.errorCode == 0) {
            self.loadTable();
            $("#popup").modal('hide');
            $.alert('This Type has been updated!');

          } else {
            $.alert(data.message);
          }
        },
        error: function (data) {
          console.log(data);
        }
      });
    }
  });
    

}

  private loadTable() {
    $.ajax({
      url: getAllVideoTypesURL,
      type: getAllVideoTypesMethod,
      success: function (data) {
        console.log(data);
        if (data.errorCode == 0) {
          tbl.clear().draw();
          tbl.rows.add(data.data); // Add new data
          tbl.columns.adjust().draw(); // Redraw the DataTable

          videoTypes = data.data;
        } else {
          alert("fail");
        }

      },
      error: function (data) {
        console.log(data);
      }
    });
  }

    private bindTableEvents() {
      $("i[data-group=grpDelete]").off('click').click(function () {
        var rowId = $(this).closest('tr').attr('id');
        $.confirm({
          title: 'Confirm!',
          content: 'Are you sure you want to delete this row?',
          buttons: {
            yes: {
              text: 'yes',
              btnClass: 'btn-red',
              action: function () {
  
                $.ajax({
                  url: deleteVideoTypesURL + rowId,
                  type: deleteVideoTypesMethod,
                  success: function (data) {
                    if (data.errorCode == 0) {
                      $.alert('This type has been deleted!');
                      self.loadTable();
                    } else {
                      $.alert('This type has not been deleted!');
                    }
  
                  },
                  error: function (data) {
                    console.log(data);
                  }
                });
              }
            },
            no: function () { },
          }
        });
  
      });

      $("i[data-group=grpEdit]").off('click').click(function () {
        //clicked material id
        rowId = $(this).closest('tr').attr('id');
        $("#hidId").val(rowId);
        videoTypesData = null;
        for (var i = 0; i < videoTypes.length; i++) {
          if (rowId == videoTypes[i].id) {
           videoTypesData = videoTypes[i];
            break;
          }
  
        }
        console.log(videoTypesData);
        if (videoTypesData != null) {
          $("#statusForm").show();
          $("#popup").modal('show');
        }
  
      });
    }

    getTitle(boolVal: boolean): string{
      if (boolVal) return "Available"
      else return "Unavailable";
    }

}


