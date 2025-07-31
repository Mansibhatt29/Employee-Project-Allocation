import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProjectsWithoutEmployeesComponent } from './projects-without-employees.component';

describe('ProjectsWithoutEmployeesComponent', () => {
  let component: ProjectsWithoutEmployeesComponent;
  let fixture: ComponentFixture<ProjectsWithoutEmployeesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProjectsWithoutEmployeesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProjectsWithoutEmployeesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
