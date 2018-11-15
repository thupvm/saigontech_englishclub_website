import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientTipComponent } from './client-tip.component';

describe('ClientTipComponent', () => {
  let component: ClientTipComponent;
  let fixture: ComponentFixture<ClientTipComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ClientTipComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ClientTipComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
