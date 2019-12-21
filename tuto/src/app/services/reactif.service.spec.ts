import { TestBed } from '@angular/core/testing';

import { ReactifService } from './reactif.service';

describe('ReactifService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ReactifService = TestBed.get(ReactifService);
    expect(service).toBeTruthy();
  });
});
