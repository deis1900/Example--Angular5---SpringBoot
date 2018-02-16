import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SidenavDrawerOverviewMainComponent } from './sidenav-drawer-overview-main.component';

describe('SidenavDrawerOverviewMainComponent', () => {
  let component: SidenavDrawerOverviewMainComponent;
  let fixture: ComponentFixture<SidenavDrawerOverviewMainComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SidenavDrawerOverviewMainComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SidenavDrawerOverviewMainComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
