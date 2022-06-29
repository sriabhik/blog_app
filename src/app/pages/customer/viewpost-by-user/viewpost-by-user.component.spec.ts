import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewpostByUserComponent } from './viewpost-by-user.component';

describe('ViewpostByUserComponent', () => {
  let component: ViewpostByUserComponent;
  let fixture: ComponentFixture<ViewpostByUserComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewpostByUserComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewpostByUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
