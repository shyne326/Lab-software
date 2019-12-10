import { TestBed } from '@angular/core/testing';

import { ValeurDeReferenceService } from './valeur-de-reference.service';

describe('ValeurDeReferenceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ValeurDeReferenceService = TestBed.get(ValeurDeReferenceService);
    expect(service).toBeTruthy();
  });
});
