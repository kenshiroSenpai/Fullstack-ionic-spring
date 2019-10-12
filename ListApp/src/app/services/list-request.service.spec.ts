import { TestBed } from '@angular/core/testing';

import { ListRequestService } from './list-request.service';

describe('ListRequestService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ListRequestService = TestBed.get(ListRequestService);
    expect(service).toBeTruthy();
  });
});
