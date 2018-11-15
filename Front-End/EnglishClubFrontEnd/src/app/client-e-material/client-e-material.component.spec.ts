import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientEMaterialComponent } from './client-e-material.component';

describe('ClientEMaterialComponent', () => {
  let component: ClientEMaterialComponent;
  let fixture: ComponentFixture<ClientEMaterialComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ClientEMaterialComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ClientEMaterialComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
