import { TestBed } from '@angular/core/testing';

import { ValidationGuardService } from './validation-guard.service';

describe('ValidationGuardService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ValidationGuardService = TestBed.get(ValidationGuardService);
    expect(service).toBeTruthy();
  });
});
