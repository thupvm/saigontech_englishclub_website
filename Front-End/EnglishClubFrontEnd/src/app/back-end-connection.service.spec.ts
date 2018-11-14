import { TestBed } from '@angular/core/testing';

import { BackEndConnectionService } from './back-end-connection.service';

describe('BackEndConnectionService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: BackEndConnectionService = TestBed.get(BackEndConnectionService);
    expect(service).toBeTruthy();
  });
});
