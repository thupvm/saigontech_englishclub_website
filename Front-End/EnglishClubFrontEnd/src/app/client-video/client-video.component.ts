import { Component, OnInit } from '@angular/core';
declare var $: any;

@Component({
  selector: 'app-client-video',
  templateUrl: './client-video.component.html',
  styleUrls: ['./client-video.component.css']
})
export class ClientVideoComponent implements OnInit {

  constructor() { }

  ngOnInit() {
    $("#videoTAB").attr('class','bg-light');
    
    $("#hihi").fancybox({
      maxWidth    : 800,
      maxHeight   : 600,
      fitToView   : false,
      width       : '70%',
      height      : '70%',
      autoSize    : false,
      closeClick  : false,
      openEffect  : 'none',
      closeEffect : 'none'
    });
  }

}
