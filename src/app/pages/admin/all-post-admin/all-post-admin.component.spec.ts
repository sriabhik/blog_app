import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllPostAdminComponent } from './all-post-admin.component';

describe('AllPostAdminComponent', () => {
  let component: AllPostAdminComponent;
  let fixture: ComponentFixture<AllPostAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AllPostAdminComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AllPostAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
