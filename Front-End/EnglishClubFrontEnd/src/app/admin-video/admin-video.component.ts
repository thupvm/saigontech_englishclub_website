import { Component, OnInit } from '@angular/core';
import { SecureApiService } from '../secure-api.service';
import { CookieService } from 'ngx-cookie-service';
declare var $: any;
var self: any;
var tbl: any;

var videoData: any;
var videos: any;


var getAllVideoTypeURL: string;
var getAllVideoTypeMethod: string;
var getAllVideoURL: string;
var getAllVideoMethod: string;
var deleteVideoURL: string;
var deleteVideoMethod: string;
var addVideoURL: string;
var addVideoMethod: string;
var updateVideoURL: string;
var updateVideoMethod: string;


@Component({
  selector: 'app-admin-video',
  templateUrl: './admin-video.component.html',
  styleUrls: ['./admin-video.component.css'],
  providers: [SecureApiService, CookieService]
})
export class AdminVideoComponent implements OnInit {
  videoTypes: any;
  constructor(private secureApi: SecureApiService, private cookie: CookieService) { }

  ngOnInit() {
    $("#ComponentTitle").text("Videos Management");
    self = this;

    getAllVideoTypeURL = this.secureApi.videoType.getAll.url;
    getAllVideoTypeMethod = this.secureApi.videoType.getAll.method;
    getAllVideoURL = this.secureApi.video.getAll.url;
    getAllVideoMethod = this.secureApi.video.getAll.method;
    deleteVideoURL = this.secureApi.video.remove.url;
    deleteVideoMethod = this.secureApi.video.remove.method;
    addVideoURL = this.secureApi.video.add.url;
    addVideoMethod = this.secureApi.video.add.method;
    updateVideoURL = this.secureApi.video.update.url;
    updateVideoMethod = this.secureApi.video.update.method;


    $.ajax({
      url: getAllVideoTypeURL,
      type: getAllVideoTypeMethod,
      success: function (data) {
        self.videoTypes = data.data;
        console.log(self.videoTypes);

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
        { data: "videotype.name" },
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
        // $("select[name=tbl_length]").select2({ width: '80px', minimumResultsForSearch: -1 });
      },
      drawCallback: function (settings) {
        self.bindTableEvents();
      }
    });

    // $("#radMale, #radFemale").iCheck({
    //   checkboxClass: 'icheckbox_square-blue',
    //   radioClass: 'iradio_square-blue',
    //   increaseArea: '20%' // optional
    // });

    //initialize modal
    $("#popup").modal({ show: false }).on('show.bs.modal', function () {
      var id = $("#hidId").val();
      if (id == 0) { //add
        $("#txtTitle").val("");
        $("#txtDescription").val("");
        $("#txtLink").val("");
      } else {
       
        $("#videoType").val(videoData.videotype.id);
        $("#txtTitle").val(videoData.title);
        $("#txtDescription").val(videoData.description);
        $("#txtLink").val(videoData.link);
        $("#txtLink").val(videoData.link);
        $("#videoStatus").val(videoData.status+"");
      }
    });

    $("#btnAdd").click(function () {
      $("#hidId").val(0);
      $("#popup").modal('show');
      $("#statusForm").hide();

    });

    $("#btnSave").click(function () {
      var date = new Date();


      var id = $("#hidId").val();
      if (id == 0) {
        var addData = {
          "adminID": self.cookie.get("adminID"),
          "videoTypeID": $("#videoType").val(),
          "title": $("#txtTitle").val(),
          "description": $("#txtDescription").val(),
          "link": $("#txtLink").val(),
          "postdate": self.date2str(new Date, "dd/MM/yyyy"),
          "status": true
        }

        console.log(addData);

        $.ajax({
          url: addVideoURL,
          data: JSON.stringify(addData),
          type: addVideoMethod,
          contentType: "application/json",
          success: function (data) {
            if (data.errorCode == 0) {
              self.loadTable();
              $("#popup").modal('hide');
              $.alert('Video has been added!');

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
          "id": videoData.id,
          "adminID": self.cookie.get("adminID"),
          "videoTypeID": $("#videoType").val(),
          "title": $("#txtTitle").val(),
          "description": $("#txtDescription").val(),
          "link": $("#txtLink").val(),
          "postdate": self.date2str(new Date, "dd/MM/yyyy"),
          "status": $("#videoStatus").val()
        }

        $.ajax({
          url: updateVideoURL,
          data: JSON.stringify(updateData),
          type: updateVideoMethod,
          contentType: "application/json",
          success: function (data) {
            if (data.errorCode == 0) {
              self.loadTable();
              $("#popup").modal('hide');
              $.alert('Video has been updated!');

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
      url: getAllVideoURL,
      type: getAllVideoMethod,
      success: function (data) {
        if (data.errorCode == 0) {
          tbl.clear().draw();
          tbl.rows.add(data.data); // Add new data
          tbl.columns.adjust().draw(); // Redraw the DataTable
          videos = data.data;
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
                url: deleteVideoURL + rowId,
                type: deleteVideoMethod,
                success: function (data) {
                  if (data.errorCode == 0) {
                    $.alert('Video has been deleted!');
                    self.loadTable();
                  } else {
                    $.alert('Video has not been deleted!');
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
      videoData = null;
      for (var i = 0; i < videos.length; i++) {
        if (rowId == videos[i].id) {
          videoData = videos[i];
          break;
        }

      }
      console.log(videoData);
      if (videoData != null){
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
