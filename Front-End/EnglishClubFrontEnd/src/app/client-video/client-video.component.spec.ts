import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientVideoComponent } from './client-video.component';

describe('ClientVideoComponent', () => {
  let component: ClientVideoComponent;
  let fixture: ComponentFixture<ClientVideoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ClientVideoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ClientVideoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
