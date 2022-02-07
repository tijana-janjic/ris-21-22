import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AgentArticlesComponent } from './agent-articles.component';

describe('AgentArticlesComponent', () => {
  let component: AgentArticlesComponent;
  let fixture: ComponentFixture<AgentArticlesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AgentArticlesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AgentArticlesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
