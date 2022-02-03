import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CityTourDialogComponent } from './city-tour-dialog.component';

describe('CityTourDialogComponent', () => {
  let component: CityTourDialogComponent;
  let fixture: ComponentFixture<CityTourDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CityTourDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CityTourDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
