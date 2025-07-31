import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetMatchingProjectsComponent } from './get-matching-projects.component';

describe('GetMatchingProjectsComponent', () => {
  let component: GetMatchingProjectsComponent;
  let fixture: ComponentFixture<GetMatchingProjectsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GetMatchingProjectsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GetMatchingProjectsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
