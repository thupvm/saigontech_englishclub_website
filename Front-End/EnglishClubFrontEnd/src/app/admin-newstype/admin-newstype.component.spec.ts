import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminNewstypeComponent } from './admin-newstype.component';

describe('AdminNewstypeComponent', () => {
  let component: AdminNewstypeComponent;
  let fixture: ComponentFixture<AdminNewstypeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminNewstypeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminNewstypeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
