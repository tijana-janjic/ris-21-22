import { TestBed } from '@angular/core/testing';

import { AgentGuard } from './AgentGuard.guard';

describe('GuardGuard', () => {
  let guard: AgentGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(AgentGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
