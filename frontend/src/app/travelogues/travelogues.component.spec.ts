import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TraveloguesComponent } from './travelogues.component';

describe('TraveloguesComponent', () => {
  let component: TraveloguesComponent;
  let fixture: ComponentFixture<TraveloguesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TraveloguesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TraveloguesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
