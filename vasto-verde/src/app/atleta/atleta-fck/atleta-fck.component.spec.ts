import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AtletaFckComponent } from './atleta-fck.component';

describe('AtletaFckComponent', () => {
  let component: AtletaFckComponent;
  let fixture: ComponentFixture<AtletaFckComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AtletaFckComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AtletaFckComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
