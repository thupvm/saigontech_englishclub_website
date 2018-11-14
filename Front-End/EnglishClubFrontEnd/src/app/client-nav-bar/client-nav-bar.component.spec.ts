import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientNavBarComponent } from './client-nav-bar.component';

describe('ClientNavBarComponent', () => {
  let component: ClientNavBarComponent;
  let fixture: ComponentFixture<ClientNavBarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ClientNavBarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ClientNavBarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
