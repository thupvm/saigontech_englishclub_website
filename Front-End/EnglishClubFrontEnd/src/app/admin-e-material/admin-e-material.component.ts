import { Component, OnInit, } from '@angular/core';
import { SecureApiService } from '../secure-api.service';
import { CookieService } from 'ngx-cookie-service';
import { ConnectionService } from '../connection.service';
declare var $: any;
var self: any;
var tbl: any;

var eMaterialData: any;
var eMaterials: any;

var ebookFiles: any;

var getAllEMaterialURL: string;
var getAllEMaterialMethod: string;

var addEMaterialURL: string;
var addEMaterialMethod: string;

var updateEMaterialURL: string;
var updateEMaterialMethod: string;

var deleteEMaterialURL: string;
var deleteEMaterialMethod: string;

var getAllEMaterialTypeURL: string;
var getAllEMaterialTypeMethod: string;

var getAllFileByMaterialIDURL: string;
var getAllFileByMaterialIDMethod: string;

var DeleteFileURL: string;
var DeleteFileMethod: string;

var fileLink: string;
var rowId: number;

var addFileURL: string;
var addFileMethod: string;



@Component({
  selector: 'app-admin-e-material',
  templateUrl: './admin-e-material.component.html',
  styleUrls: ['./admin-e-material.component.css'],
  providers: [SecureApiService, CookieService, ConnectionService]
})
export class AdminEMaterialComponent implements OnInit {
  eMaterialTypes: any;
  constructor(private connection: ConnectionService, private secureApi: SecureApiService, private cookie: CookieService) { }
  ebooks: any;
  ngOnInit() {
    $("#ComponentTitle").text("e-Materials Management");
    self = this;

    getAllEMaterialURL = this.secureApi.ematerial.getAll.url;
    getAllEMaterialMethod = this.secureApi.ematerial.getAll.method;

    addEMaterialURL = this.secureApi.ematerial.add.url;
    addEMaterialMethod = this.secureApi.ematerial.add.method;

    updateEMaterialURL = this.secureApi.ematerial.update.url;
    updateEMaterialMethod = this.secureApi.ematerial.update.method;

    deleteEMaterialURL = this.secureApi.ematerial.remove.url;
    deleteEMaterialMethod = this.secureApi.ematerial.remove.method;

    getAllEMaterialTypeURL = this.secureApi.ematerialTypes.getAllActive.url;
    getAllEMaterialTypeMethod = this.secureApi.ematerialTypes.getAllActive.method;

    getAllFileByMaterialIDURL = this.secureApi.file.getAll.url;
    getAllFileByMaterialIDMethod = this.secureApi.file.getAll.method;

    DeleteFileURL = this.secureApi.file.remove.url;
    DeleteFileMethod = this.secureApi.file.remove.method;

    addFileURL = this.secureApi.file.add.url;
    addFileMethod = this.secureApi.file.add.method;

    fileLink = this.connection.fileLink;

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
        $("#txtContent").froalaEditor();
        $("#eMaterialType").val(eMaterialData.materialtype.id);
        $("#txtTitle").val(eMaterialData.title);
        $("#txtContent").froalaEditor('html.set', eMaterialData.content);
        $("#eMaterialStatus").val(eMaterialData.status + "");
      }
    });

    $("#btnAdd").click(function () {
      $("#hidId").val(0);
      $("#popup").modal('show');
      $("#statusForm").hide();
      $("#ebookList").hide();
      $("#ebookForm").show();
    });

    $("#addEbookBTN").click(function () {
      $("#ebookContainer").append("<input name='ebookChooser' type='file' class='form-control-file mb-2'/>");
    });

    $("#btnSave").click(function () {
      var id = $("#hidId").val();
      if (id == 0) {
        var addData: FormData = new FormData();
        addData.append("adminID", self.cookie.get("adminID"));
        addData.append("eMaterialTypeID", $("#eMaterialType").val());
        addData.append("titleImage", $("#fileChooser")[0].files[0]);
        addData.append("title", $("#txtTitle").val());
        addData.append("content", $("#txtContent").val());

        addData.append("postdate", self.date2str(new Date, "dd/MM/yyyy"));
        $('input[name=ebookChooser]').each(function () {
          addData.append("ebooks", $(this)[0].files[0]);
        });

        $.ajax({
          url: addEMaterialURL,
          data: addData,
          type: addEMaterialMethod,
          processData: false,
          contentType: false,
          success: function (data) {
            if (data.errorCode == 0) {
              self.loadTable();
              $("#popup").modal('hide');
              $.alert('Material has been added!');

            } else {
              $.alert(data.message);
            }
          },
          error: function (data) {
            console.log(data);
          }
        });
      } else {
        var updateData = new FormData();

        updateData.append("adminID", self.cookie.get("adminID"));
        updateData.append("eMaterialTypeID", $("#eMaterialType").val());

        updateData.append("title", $("#txtTitle").val());
        updateData.append("content", $("#txtContent").val());
        if ($("#fileChooser")[0].files[0])
          updateData.append("titleImage", $("#fileChooser")[0].files[0]);
        else updateData.append("titleImage", null);
        updateData.append("postdate", self.date2str(new Date, "dd/MM/yyyy"));
        updateData.append("status", $("#eMaterialStatus").val());

        $.ajax({
          url: updateEMaterialURL + eMaterialData.id,
          data: updateData,
          type: updateEMaterialMethod,
          processData: false,
          contentType: false,
          success: function (data) {
            if (data.errorCode == 0) {
              self.loadTable();
              $("#popup").modal('hide');
              $.alert('Material has been updated!');

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

    $("#addEbookInput").hide();
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
                    $.alert('Material has been deleted!');
                    self.loadTable();
                  } else {
                    $.alert('Material has not been deleted!');
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
      eMaterialData = null;
      for (var i = 0; i < eMaterials.length; i++) {
        if (rowId == eMaterials[i].id) {
          eMaterialData = eMaterials[i];
          break;
        }

      }
      console.log(eMaterialData);
      if (eMaterialData != null) {
        $("#statusForm").show();
        $("#ebookList").show();
        $("#ebookForm").hide();
        $("#popup").modal('show');
      }
      //load ebook files of clicked material
      $.ajax({
        url: getAllFileByMaterialIDURL + rowId,
        type: getAllFileByMaterialIDMethod,
        success: function (data) {
          console.log(data);
          //load list of file to table
          self.ebooks = data.data;
          for (var i = 0; i < self.ebooks.length; i++) {
            console.log(fileLink + self.ebooks[i].name);
          }
        },
        error: function (data) {
          return null;
        }
      });

    });
  }

  private date2str(x, y) {
    var z = {
      M: x.getMonth() + 1,
      d: x.getDate(),
      h: x.getHours(),
      m: x.getMinutes(),
      s: x.getSeconds()
    };
    y = y.replace(/(M+|d+|h+|m+|s+)/g, function (v) {
      return ((v.length > 1 ? "0" : "") + eval('z.' + v.slice(-1))).slice(-2)
    });

    return y.replace(/(y+)/g, function (v) {
      return x.getFullYear().toString().slice(-v.length)
    });
  }

  deleteEbook(ebookID: number) {

    $.confirm({
      title: 'Confirm!',
      content: 'Are you sure you want to delete this row?',
      buttons: {
        yes: {
          text: 'yes',
          btnClass: 'btn-red',
          action: function () {
            $.ajax({
              url: DeleteFileURL + ebookID,
              type: DeleteFileMethod,
              success: function (data) {
                if (data.errorCode == 0) {
                  $.alert('Ebook has been deleted!');

                  $.ajax({
                    url: getAllFileByMaterialIDURL + rowId,
                    type: getAllFileByMaterialIDMethod,
                    success: function (data) {
                      console.log(data);
                      //load list of file to table
                      self.ebooks = data.data;
                      for (var i = 0; i < self.ebooks.length; i++) {
                        console.log(fileLink + self.ebooks[i].name);
                      }
                    },
                    error: function (data) {
                      return null;
                    }
                  });

                } else {
                  $.alert('Ebook has not been deleted!');
                }

              },
              error: function (data) {
                console.log(data);
              }
            });
          }

        }, no: function () { }
      }





    });
  }

  getDownloadLink(fileName: string) {
    return this.connection.fileLink + fileName;
  }

  addEbookListener() {
    if ($("#addEbookButton").text() == "Add Ebook"){
      $("#addEbookInput").show();
      $("#addEbookButton").text("Done");
      $("#addEbookButton").removeClass('btn-primary').addClass('btn-warning');
    }
    else {
      

      
      //add ebook to this material

      var fileData = new FormData();
      fileData.append("materialID", rowId+"");
      fileData.append("ebookFile", $("#ebookChooser")[0].files[0])
      fileData.append("status", "true");


      $.ajax({
        url: addFileURL,
        data: fileData,
        type: addFileMethod,
        processData: false,
        contentType: false,
        success: function (data) {
          if (data.errorCode == 0) {
          
            $.alert('File has been updated!');

            $.ajax({
              url: getAllFileByMaterialIDURL + rowId,
              type: getAllFileByMaterialIDMethod,
              success: function (data) {
                console.log(data);
                //load list of file to table
                self.ebooks = data.data;
              },
              error: function (data) {
                return null;
              }
            });

          } else {
            $.alert(data.message);
          }
        },
        error: function (data) {
          console.log(data);
        }
      });

      $("#addEbookInput").hide();
      $("#addEbookButton").text("Add Ebook");
      $("#addEbookButton").removeClass('btn-warning').addClass('btn-primary');

    }
    
  }
}
