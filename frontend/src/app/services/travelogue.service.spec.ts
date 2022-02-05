import { TestBed } from '@angular/core/testing';

import { TravelogueService } from './travelogue.service';

describe('TravelogueService', () => {
  let service: TravelogueService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TravelogueService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
