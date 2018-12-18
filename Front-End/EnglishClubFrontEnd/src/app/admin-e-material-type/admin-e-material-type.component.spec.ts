import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminEMaterialTypeComponent } from './admin-e-material-type.component';

describe('AdminEMaterialTypeComponent', () => {
  let component: AdminEMaterialTypeComponent;
  let fixture: ComponentFixture<AdminEMaterialTypeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminEMaterialTypeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminEMaterialTypeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
