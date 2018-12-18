import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminVideoTypeComponent } from './admin-video-type.component';

describe('AdminVideoTypeComponent', () => {
  let component: AdminVideoTypeComponent;
  let fixture: ComponentFixture<AdminVideoTypeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminVideoTypeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminVideoTypeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
