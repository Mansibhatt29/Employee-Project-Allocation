import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewAssignedProjectComponent } from './view-assigned-project.component';

describe('ViewAssignedProjectComponent', () => {
  let component: ViewAssignedProjectComponent;
  let fixture: ComponentFixture<ViewAssignedProjectComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ViewAssignedProjectComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ViewAssignedProjectComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
