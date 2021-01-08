import { TestBed, async } from '@angular/core/testing';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';

describe('AppComponent', () => {
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        AppComponent
      ],
      imports: [
        BrowserModule,
        FormsModule,
        ReactiveFormsModule,
        HttpModule
      ],
      providers: []
    }).compileComponents();
  }));


  it('form should be invalid when empty', () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    expect(app.paymentForm.valid).toBeFalsy();
  });

  it('card number validation should work', async(() => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    app.paymentForm.controls['cardNumber'].setValue("1234123412345678");
    expect(app.paymentForm.controls['cardNumber'].valid).toBeTruthy();
  }));

  it('expiration date validation should work', async(() => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    app.paymentForm.controls['expirationMonth'].setValue("134");
    app.paymentForm.controls['expirationYear'].setValue("2017");
    expect(app.paymentForm.controls['expirationMonth'].valid).toBeFalsy();
    expect(app.paymentForm.controls['expirationYear'].valid).toBeTruthy();
  }));

  it('cvv/cvc validation should work', async(() => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    app.paymentForm.controls['cvv'].setValue("677");
    expect(app.paymentForm.controls['cvv'].valid).toBeTruthy();
  }));

  it('submit button should display the success message', async(() => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    app.paymentForm.controls['nameOnCard'].setValue("Mathew Jerome");
    app.paymentForm.controls['cardNumber'].setValue("3456234567854567");
    app.paymentForm.controls['expirationMonth'].setValue("11");
    app.paymentForm.controls['expirationYear'].setValue("2017");
    app.paymentForm.controls['cvv'].setValue("677");
    app.submitForm();
    expect(app.displayMessage).toBe("Payment Successful!");
  }));

  it('submit button should display the failure message', async(() => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    app.paymentForm.controls['nameOnCard'].setValue("Mathew Jerome");
    app.paymentForm.controls['cardNumber'].setValue("345623456784567");
    app.paymentForm.controls['expirationMonth'].setValue("15");
    app.paymentForm.controls['expirationYear'].setValue("2017");
    app.paymentForm.controls['cvv'].setValue("6776");
    app.submitForm();
    expect(app.displayMessage).toBe("Payment Failed!");
  }));
});
