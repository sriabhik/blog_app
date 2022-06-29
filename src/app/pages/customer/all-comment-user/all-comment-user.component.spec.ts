import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllCommentUserComponent } from './all-comment-user.component';

describe('AllCommentUserComponent', () => {
  let component: AllCommentUserComponent;
  let fixture: ComponentFixture<AllCommentUserComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AllCommentUserComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AllCommentUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
