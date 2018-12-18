import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminTipTypeComponent } from './admin-tip-type.component';

describe('AdminTipTypeComponent', () => {
  let component: AdminTipTypeComponent;
  let fixture: ComponentFixture<AdminTipTypeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminTipTypeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminTipTypeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
