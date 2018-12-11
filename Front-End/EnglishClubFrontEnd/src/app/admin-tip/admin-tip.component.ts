import { Component, OnInit } from '@angular/core';
import { SecureApiService } from '../secure-api.service';
import { CookieService } from 'ngx-cookie-service';
declare var $: any;
var self: any;
var tbl: any;

var tipData: any;
var tips: any;

var getAllTipTypeURL: string;
var getAllTipTypeMethod: string;
var getAllTipURL: string;
var getAllTipMethod: string;
var deleteTipURL: string;
var deleteTipMethod: string;
var addTipURL: string;
var addTipMethod: string;
var updateTipURL: string;
var updateTipMethod: string;

@Component({
  selector: 'app-admin-tip',
  templateUrl: './admin-tip.component.html',
  styleUrls: ['./admin-tip.component.css'],
  providers: [SecureApiService, CookieService]
})
export class AdminTipComponent implements OnInit {
  tipTypes: any;
  constructor(private secureApi: SecureApiService, private cookie: CookieService) { }

  ngOnInit() {
    $("#ComponentTitle").text("Tips Management");
    self = this;

    getAllTipTypeURL = this.secureApi.tipType.getAll.url;
    getAllTipTypeMethod = this.secureApi.tipType.getAll.method;
    getAllTipURL = this.secureApi.tip.getAll.url;
    getAllTipMethod = this.secureApi.tip.getAll.method;
    deleteTipURL = this.secureApi.tip.remove.url;
    deleteTipMethod = this.secureApi.tip.remove.method;
    addTipURL = this.secureApi.tip.add.url;
    addTipMethod = this.secureApi.tip.add.method;
    updateTipURL = this.secureApi.tip.update.url;
    updateTipMethod = this.secureApi.tip.update.method;

    $.ajax({
      url: getAllTipTypeURL,
      type: getAllTipTypeMethod,
      success: function (data) {
        self.tipTypes = data.data;
        console.log(self.tipTypes);

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
        { data: "tiptype.name" },
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
       
        $("#tipType").val(tipData.tiptype.id);
        $("#txtTitle").val(tipData.title);
        $("#txtContent").froalaEditor('html.set', tipData.content);
        $("#tipStatus").val(tipData.status+"");
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
      var date = new Date();


      var id = $("#hidId").val();
      if (id == 0) {
        var addData = new FormData();
        addData.append("adminID", self.cookie.get("adminID"));
        addData.append("tipTypeID", $("#tipType").val());
        addData.append("title", $("#txtTitle").val());
        addData.append("content", $("#txtContent").val());
        addData.append("titleImage", $("#fileChooser")[0].files[0]);
        addData.append("postDate", self.date2str(new Date, "dd/MM/yyyy"));
       

        

        $.ajax({
          url: addTipURL,
          data: addData,
          type: addTipMethod,
          processData: false,
          contentType: false,
          success: function (data) {
            if (data.errorCode == 0) {
              self.loadTable();
              $("#popup").modal('hide');
              $.alert('Tip has been added!');

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
        updateData.append("tipTypeID", $("#tipType").val());
        updateData.append("title", $("#txtTitle").val());
        updateData.append("content", $("#txtContent").val());
        if ($("#fileChooser")[0].files[0])
          updateData.append("titleImage", $("#fileChooser")[0].files[0]);
        else updateData.append("titleImage", null);
        updateData.append("postDate", self.date2str(new Date, "dd/MM/yyyy"));
        updateData.append("status", $("#tipStatus").val());

        $.ajax({
          url: updateTipURL+tipData.id,
          data: updateData,
          type: updateTipMethod,
          processData: false,
          contentType: false,
          success: function (data) {
            if (data.errorCode == 0) {
              self.loadTable();
              $("#popup").modal('hide');
              $.alert('Tip has been updated!');

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
      url: getAllTipURL,
      type: getAllTipMethod,
      success: function (data) {
        console.log(data);
        if (data.errorCode == 0) {
          tbl.clear().draw();
          tbl.rows.add(data.data); // Add new data
          tbl.columns.adjust().draw(); // Redraw the DataTable
          tips = data.data;
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
                url: deleteTipURL + rowId,
                type: deleteTipMethod,
                success: function (data) {
                  if (data.errorCode == 0) {
                    $.alert('Tip has been deleted!');
                    self.loadTable();
                  } else {
                    $.alert('Tip has not been deleted!');
                  }

                },
                error: function (data) {
                  return null;
                }
              });
            }
          },
          no: function () { },
        }
      });

    });

    $("i[data-group=grpEdit]").off('click').click(function () {
      var rowId = $(this).closest('tr').attr('id');
      $("#hidId").val(rowId);
      tipData = null;
      for (var i = 0; i < tips.length; i++) {
        if (rowId == tips[i].id) {
          tipData = tips[i];
          break;
        }

      }
      console.log(tipData);
      if (tipData != null){
        $("#statusForm").show();
        $("#popup").modal('show');
      }

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

}
