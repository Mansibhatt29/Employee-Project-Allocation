import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeesWithoutProjectsComponent } from './employees-without-projects.component';

describe('EmployeesWithoutProjectsComponent', () => {
  let component: EmployeesWithoutProjectsComponent;
  let fixture: ComponentFixture<EmployeesWithoutProjectsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EmployeesWithoutProjectsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EmployeesWithoutProjectsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
