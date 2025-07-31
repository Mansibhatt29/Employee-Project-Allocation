import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateProjectDetailsComponent } from './update-project-details.component';

describe('UpdateProjectDetailsComponent', () => {
  let component: UpdateProjectDetailsComponent;
  let fixture: ComponentFixture<UpdateProjectDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UpdateProjectDetailsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UpdateProjectDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
