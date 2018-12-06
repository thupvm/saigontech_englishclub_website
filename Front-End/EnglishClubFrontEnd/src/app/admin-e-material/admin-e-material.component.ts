import { Component, OnInit } from '@angular/core';
import { SecureApiService } from '../secure-api.service';
import { CookieService } from 'ngx-cookie-service';
import FilePondPluginImagePreview from 'filepond-plugin-image-preview';

declare var $: any;
var self: any;
var tbl: any;

var eMaterialData: any;
var eMaterials: any;

var getAllEMaterialURL: string;
var getAllEMaterialMethod: string;

var deleteEMaterialURL: string;
var deleteEMaterialMethod: string;

var getAllEMaterialTypeURL: string;
var getAllEMaterialTypeMethod: string;

@Component({
  selector: 'app-admin-e-material',
  templateUrl: './admin-e-material.component.html',
  styleUrls: ['./admin-e-material.component.css'],
  providers: [SecureApiService, CookieService]
})
export class AdminEMaterialComponent implements OnInit {
  eMaterialTypes: any;
  constructor(private secureApi: SecureApiService, private cookie: CookieService) { }

  ngOnInit() {
    $("#ComponentTitle").text("e-Materials Management");
    self = this;

    getAllEMaterialURL = this.secureApi.ematerial.getAll.url;
    getAllEMaterialMethod = this.secureApi.ematerial.getAll.method;

    deleteEMaterialURL = this.secureApi.ematerial.remove.url;
    deleteEMaterialMethod = this.secureApi.ematerial.remove.method;

    getAllEMaterialTypeURL = this.secureApi.ematerialType.getAll.url;
    getAllEMaterialTypeMethod = this.secureApi.ematerialType.getAll.method;

    $.ajax({
      url: getAllEMaterialTypeURL,
      type: getAllEMaterialTypeMethod,
      success: function (data) {
        self.eMaterialTypes = data.data;
        console.log(self.eMaterialTypes);

      },
      error: function (data) {
        return null;
      }
    });


    tbl = $("#tbl").DataTable({
      columnDefs: [
        { orderable: false, targets: [0, 6] }
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
        { data: "title" },
        { data: "materialtype.name" },
        { data: "postdate" },
        { data: "admin.username" },
        { data: "status" },
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
        $("#txtTitle").val("");
        $("#txtContent").val("");
        $("#txtPicture").val("");
      } else {

        // $("#tipType").val(tipData.tiptype.id);
        // $("#txtTitle").val(tipData.title);
        // $("#txtContent").froalaEditor('html.set', tipData.content);
        // $("#tipStatus").val(tipData.status+"");
      }
    });

    $("#btnAdd").click(function () {
      $("#hidId").val(0);
      $("#popup").modal('show');
      $("#statusForm").hide();
    });

    //-----------------------------------------------------
    // First register any plugins
    $.fn.filepond.registerPlugin(FilePondPluginImagePreview);

    // Turn input element into a pond
    $('.my-pond').filepond();

    // Set allowMultiple property to true
    $('.my-pond').filepond('allowMultiple', true);
  
    // Listen for addfile event
    $('.my-pond').on('FilePond:addfile', function(e) {
      console.log('file added event', e);
    });
    $("#btnSave").click(function(){
      alert("hihi");
      // console.log($('.my-pond').filepond('getFiles'));
      var fileArr = $('.my-pond').filepond('getFiles');
      var fd = new FormData();
      for (var i = 0 ; i < fileArr.length; i++){
        fd.append("files", fileArr[i]);
      }
      $.ajax({
        url: "http://localhost:8080/SaigonTechEnglishClubBackEnd/uploadFile",
        data: fd,
        type: "POST",
        processData: false,
        contentType: false,
        success: function (data) {
          console.log(data);
        },
        error: function (data) {
          console.log(data);
        }
      });
    });
  }

  private loadTable() {

    $.ajax({
      url: getAllEMaterialURL,
      type: getAllEMaterialMethod,
      success: function (data) {
        console.log(data);
        if (data.errorCode == 0) {
          tbl.clear().draw();
          tbl.rows.add(data.data); // Add new data
          tbl.columns.adjust().draw(); // Redraw the DataTable
          eMaterials = data.data;
        } else {
          alert("fail");
        }

      },
      error: function (data) {
        return null;
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
                url: deleteEMaterialURL + rowId,
                type: deleteEMaterialMethod,
                success: function (data) {
                  if (data.errorCode == 0) {
                    $.alert('e-Material has been deleted!');
                    self.loadTable();
                  } else {
                    $.alert('e-Material has not been deleted!');
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
  }

}
