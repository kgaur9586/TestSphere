import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewCategoriesComponent } from './view-categories.component';

describe('ViewCategoriesComponent', () => {
  let component: ViewCategoriesComponent;
  let fixture: ComponentFixture<ViewCategoriesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ViewCategoriesComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ViewCategoriesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
