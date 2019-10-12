import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InListPage } from './in-list.page';

describe('InListPage', () => {
  let component: InListPage;
  let fixture: ComponentFixture<InListPage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InListPage ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA],
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InListPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
