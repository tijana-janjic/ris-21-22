import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GuideArticlesComponent } from './guide-articles.component';

describe('GuideArticlesComponent', () => {
  let component: GuideArticlesComponent;
  let fixture: ComponentFixture<GuideArticlesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GuideArticlesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GuideArticlesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
