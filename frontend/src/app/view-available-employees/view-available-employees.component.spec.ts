import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewAvailableEmployeesComponent } from './view-available-employees.component';

describe('ViewAvailableEmployeesComponent', () => {
  let component: ViewAvailableEmployeesComponent;
  let fixture: ComponentFixture<ViewAvailableEmployeesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ViewAvailableEmployeesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ViewAvailableEmployeesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
