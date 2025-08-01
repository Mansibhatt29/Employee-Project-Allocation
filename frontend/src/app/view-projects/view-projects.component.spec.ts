import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewProjectsComponent } from './view-projects.component';

describe('ViewProjectsComponent', () => {
  let component: ViewProjectsComponent;
  let fixture: ComponentFixture<ViewProjectsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ViewProjectsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ViewProjectsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
