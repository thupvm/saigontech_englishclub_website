import { Component, OnInit } from '@angular/core';
import { SecureApiService } from '../secure-api.service';
import { CookieService } from 'ngx-cookie-service';

declare var $: any;
var self: any;
var tbl: any;

var newsData: any;
var news: any;

var getAllNewsTypeURL: string;
var getAllNewsTypeMethod: string;
var getAllNewsURL: string;
var getAllNewsMethod: string;
var deleteNewsURL: string;
var deleteNewsMethod: string;
var addNewsURL: string;
var addNewsMethod: string;
var updateNewsURL: string;
var updateNewsMethod: string;

@Component({
  selector: 'app-admin-news',
  templateUrl: './admin-news.component.html',
  styleUrls: ['./admin-news.component.css'],
  providers: [SecureApiService, CookieService]
})
export class AdminNewsComponent implements OnInit {
  newsTypes: any;
  constructor(private secureApi: SecureApiService, private cookie: CookieService) { }

  ngOnInit() {
    $("#ComponentTitle").text("News Management");
    self = this;

    getAllNewsTypeURL = this.secureApi.newsType.getAll.url;
    getAllNewsTypeMethod = this.secureApi.newsType.getAll.method;
    getAllNewsURL = this.secureApi.news.getAll.url;
    getAllNewsMethod = this.secureApi.news.getAll.method;
    deleteNewsURL = this.secureApi.news.remove.url;
    deleteNewsMethod = this.secureApi.news.remove.method;
    addNewsURL = this.secureApi.news.add.url;
    addNewsMethod = this.secureApi.news.add.method;
    updateNewsURL = this.secureApi.news.update.url;
    updateNewsMethod = this.secureApi.news.update.method;


    $.ajax({
      url: getAllNewsTypeURL,
      type: getAllNewsTypeMethod,
      success: function (data) {
        self.newsTypes = data.data;
        console.log(self.newsTypes);

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
        { data: "newstype.name" },
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
        // self.bindTableEvents();
      }
    });

    // $("#popup").modal({ show: false }).on('show.bs.modal', function () {
    //   var id = $("#hidId").val();
    //   if (id == 0) { //add
    //     $("#txtTitle").val("");
    //     $("#txtContent").val("");
    //     $("#txtPicture").val("");
    //   } else {
       
    //     $("#newsType").val(newsData.newstype.id);
    //     $("#txtTitle").val(newsData.title);
    //     $("#txtContent").froalaEditor('html.set', newsData.content);
    //     $("#newsStatus").val(newsData.status+"");
    //   }
    // });

    // $("#btnAdd").click(function () {
    //   $("#hidId").val(0);
    //   $("#popup").modal('show');
    //   $("#statusForm").hide();
    // });

    // $('textarea#txtContent').froalaEditor({
    //   heightMin: 500,
    //   heightMax: 500,
    //   toolbarButtons: ['bold', 'italic', 'underline', 'strikeThrough', 'subscript', 'superscript', '|', 'fontFamily', 'fontSize', 'color', 'inlineStyle', 'inlineClass', 'clearFormatting', '|', 'emoticons', 'fontAwesome', 'specialCharacters', '-', 'paragraphFormat', 'lineHeight', 'paragraphStyle', 'align', 'formatOL', 'formatUL', 'outdent', 'indent', 'quote', '|', 'insertLink', 'insertImage', 'insertVideo', 'insertFile', 'insertTable', '-', 'insertHR', 'selectAll', 'getPDF', 'print', 'help', 'html', 'fullscreen', '|', 'undo', 'redo']
    // });

    // $("#btnSave").click(function(){
    //   var id = $("#hidId").val();
    //   if (id == 0) {
    //     var addData = new FormData();
    //     addData.append("adminID", self.cookie.get("adminID"));
    //     addData.append("newsTypeID", $("#newsType").val());
    //     addData.append("title", $("#txtTitle").val());
    //     addData.append("content", $("#txtContent").val());
    //     addData.append("titleImage", $("#fileChooser")[0].files[0]);
    //     addData.append("postDate", self.date2str(new Date, "dd/MM/yyyy"));

    //     $.ajax({
    //       url: addNewsURL,
    //       data: addData,
    //       type: addNewsMethod,
    //       processData: false,
    //       contentType: false,
    //       success: function (data) {
    //         if (data.errorCode == 0) {
    //           self.loadTable();
    //           $("#popup").modal('hide');
    //           $.alert('This news has been added!');

    //         } else {
    //           $.alert(data.message);
    //         }
    //       },
    //       error: function (data) {
    //         console.log(data);
    //       }
    //     });
    //   } else {

    //     var updateData = new FormData();
    //     updateData.append("adminID", self.cookie.get("adminID"));
    //     updateData.append("newsTypeID", $("#newsType").val());
    //     updateData.append("title", $("#txtTitle").val());
    //     updateData.append("content", $("#txtContent").val());
    //     if ($("#fileChooser")[0].files[0])
    //       updateData.append("titleImage", $("#fileChooser")[0].files[0]);
    //     else updateData.append("titleImage", null);
    //     updateData.append("postDate", self.date2str(new Date, "dd/MM/yyyy"));
    //     updateData.append("status", $("#newsStatus").val());

    //     $.ajax({
    //       url: updateNewsURL+newsData.id,
    //       data: updateData,
    //       type: updateNewsMethod,
    //       processData: false,
    //       contentType: false,
    //       success: function (data) {
    //         if (data.errorCode == 0) {
    //           self.loadTable();
    //           $("#popup").modal('hide');
    //           $.alert('This news has been updated!');

    //         } else {
    //           $.alert(data.message);
    //         }
    //       },
    //       error: function (data) {
    //         console.log(data);
    //       }
    //     });
    //   }
    // });
    this.loadTable();
  }

  private loadTable() {
  

    $.ajax({
      url: getAllNewsURL,
      type: getAllNewsMethod,
      success: function (data) {
        console.log(data);
        if (data.errorCode == 0) {
          tbl.clear().draw();
          tbl.rows.add(data.data); // Add new data
          tbl.columns.adjust().draw(); // Redraw the DataTable
          news = data.data;
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
                url: deleteNewsURL + rowId,
                type: deleteNewsMethod,
                success: function (data) {
                  if (data.errorCode == 0) {
                    $.alert('This news has been deleted!');
                    self.loadTable();
                  } else {
                    $.alert('This News has not been deleted!');
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
      newsData = null;
      for (var i = 0; i < news.length; i++) {
        if (rowId == news[i].id) {
          newsData = news[i];
          break;
        }

      }
      console.log(newsData);
      if (newsData != null){
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
