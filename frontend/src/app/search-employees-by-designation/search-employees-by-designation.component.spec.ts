import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchEmployeesByDesignationComponent } from './search-employees-by-designation.component';

describe('SearchEmployeesByDesignationComponent', () => {
  let component: SearchEmployeesByDesignationComponent;
  let fixture: ComponentFixture<SearchEmployeesByDesignationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SearchEmployeesByDesignationComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SearchEmployeesByDesignationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
