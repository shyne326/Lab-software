import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ValidateResultsComponent } from './validate-results.component';

describe('ValidateResultsComponent', () => {
  let component: ValidateResultsComponent;
  let fixture: ComponentFixture<ValidateResultsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ValidateResultsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ValidateResultsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
