import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PostByCategoryCustomerComponent } from './post-by-category-customer.component';

describe('PostByCategoryCustomerComponent', () => {
  let component: PostByCategoryCustomerComponent;
  let fixture: ComponentFixture<PostByCategoryCustomerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PostByCategoryCustomerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PostByCategoryCustomerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
