import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RaiseEmployeeRequestComponent } from './raise-employee-request.component';

describe('RaiseEmployeeRequestComponent', () => {
  let component: RaiseEmployeeRequestComponent;
  let fixture: ComponentFixture<RaiseEmployeeRequestComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RaiseEmployeeRequestComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RaiseEmployeeRequestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
