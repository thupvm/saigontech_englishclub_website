import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminTipComponent } from './admin-tip.component';

describe('AdminTipComponent', () => {
  let component: AdminTipComponent;
  let fixture: ComponentFixture<AdminTipComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminTipComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminTipComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
