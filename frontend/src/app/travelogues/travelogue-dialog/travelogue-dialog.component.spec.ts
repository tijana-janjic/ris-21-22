import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TravelogueDialogComponent } from './travelogue-dialog.component';

describe('TravelogueDialogComponent', () => {
  let component: TravelogueDialogComponent;
  let fixture: ComponentFixture<TravelogueDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TravelogueDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TravelogueDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
