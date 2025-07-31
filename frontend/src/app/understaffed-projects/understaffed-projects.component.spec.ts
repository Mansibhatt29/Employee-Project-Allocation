import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UnderstaffedProjectsComponent } from './understaffed-projects.component';

describe('UnderstaffedProjectsComponent', () => {
  let component: UnderstaffedProjectsComponent;
  let fixture: ComponentFixture<UnderstaffedProjectsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UnderstaffedProjectsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UnderstaffedProjectsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
