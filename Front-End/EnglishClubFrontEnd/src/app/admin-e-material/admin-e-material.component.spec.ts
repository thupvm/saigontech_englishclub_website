import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminEMaterialComponent } from './admin-e-material.component';

describe('AdminEMaterialComponent', () => {
  let component: AdminEMaterialComponent;
  let fixture: ComponentFixture<AdminEMaterialComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminEMaterialComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminEMaterialComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
