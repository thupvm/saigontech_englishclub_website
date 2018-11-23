import { TestBed } from '@angular/core/testing';

import { NonSecureApiService } from './non-secure-api.service';

describe('NonSecureApiService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: NonSecureApiService = TestBed.get(NonSecureApiService);
    expect(service).toBeTruthy();
  });
});
