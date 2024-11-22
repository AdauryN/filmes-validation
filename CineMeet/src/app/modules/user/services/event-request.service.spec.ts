import { TestBed } from '@angular/core/testing';

import { EventRequestService } from './event-request.service';

describe('EventRequestService', () => {
  let service: EventRequestService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EventRequestService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
