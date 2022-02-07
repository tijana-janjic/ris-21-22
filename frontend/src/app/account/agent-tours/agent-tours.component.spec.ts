import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AgentToursComponent } from './agent-tours.component';

describe('AgentToursComponent', () => {
  let component: AgentToursComponent;
  let fixture: ComponentFixture<AgentToursComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AgentToursComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AgentToursComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
