import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ToursFilteredComponent } from './tours-filtered.component';

describe('ToursFilteredComponent', () => {
  let component: ToursFilteredComponent;
  let fixture: ComponentFixture<ToursFilteredComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ToursFilteredComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ToursFilteredComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
