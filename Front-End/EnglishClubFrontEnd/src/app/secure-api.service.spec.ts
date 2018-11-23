import { TestBed } from '@angular/core/testing';

import { SecureApiService } from './secure-api.service';

describe('SecureApiService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SecureApiService = TestBed.get(SecureApiService);
    expect(service).toBeTruthy();
  });
});
