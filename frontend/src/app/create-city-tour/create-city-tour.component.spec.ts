import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateCityTourComponent } from './create-city-tour.component';

describe('CreateCityTourComponent', () => {
  let component: CreateCityTourComponent;
  let fixture: ComponentFixture<CreateCityTourComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateCityTourComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateCityTourComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
