import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewMatchingEmployeesComponent } from './view-matching-employees.component';

describe('ViewMatchingEmployeesComponent', () => {
  let component: ViewMatchingEmployeesComponent;
  let fixture: ComponentFixture<ViewMatchingEmployeesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ViewMatchingEmployeesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ViewMatchingEmployeesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
