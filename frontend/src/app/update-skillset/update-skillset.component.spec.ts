import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateSkillsetComponent } from './update-skillset.component';

describe('UpdateSkillsetComponent', () => {
  let component: UpdateSkillsetComponent;
  let fixture: ComponentFixture<UpdateSkillsetComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UpdateSkillsetComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UpdateSkillsetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
