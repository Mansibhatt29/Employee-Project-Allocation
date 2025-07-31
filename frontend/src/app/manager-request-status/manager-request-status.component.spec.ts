import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManagerRequestStatusComponent } from './manager-request-status.component';

describe('ManagerRequestStatusComponent', () => {
  let component: ManagerRequestStatusComponent;
  let fixture: ComponentFixture<ManagerRequestStatusComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ManagerRequestStatusComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ManagerRequestStatusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
