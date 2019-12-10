import { TestBed } from '@angular/core/testing';

import { SampleTypeService } from './sample-type.service';

describe('SampleTypeService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SampleTypeService = TestBed.get(SampleTypeService);
    expect(service).toBeTruthy();
  });
});
