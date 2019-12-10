import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ResultIntermediateComponent } from './result-intermediate.component';

describe('ResultIntermediateComponent', () => {
  let component: ResultIntermediateComponent;
  let fixture: ComponentFixture<ResultIntermediateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ResultIntermediateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ResultIntermediateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
