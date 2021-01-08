import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { Payment } from './payment';

@Component({
  selector: 'payment-form',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent {

  payment: Payment;

  paymentForm: FormGroup;
  displayMessage: string;

  constructor() {
    this.payment = new Payment();
    this.paymentForm = new FormGroup({
      'nameOnCard': new FormControl(this.payment.nameOnCard, [
        Validators.required,
        Validators.pattern("[a-zA-Z ]+")
      ]),
      'cardNumber': new FormControl(this.payment.cardNumber, [
        Validators.required,
        Validators.pattern("[0-9]+"),
        Validators.minLength(16),
        Validators.maxLength(16)
      ]),
      'expirationMonth': new FormControl(this.payment.expirationMonth, [
        Validators.required,
        Validators.min(1),
        Validators.max(12),
        Validators.minLength(2),
        Validators.maxLength(2)
      ]),
      'expirationYear': new FormControl(this.payment.expirationYear, [
        Validators.required,
        Validators.minLength(4),
        Validators.maxLength(4)
      ]),
      'cvv': new FormControl(this.payment.cvv, [
        Validators.required,
        Validators.pattern("[0-9]+"),
        Validators.minLength(3),
        Validators.maxLength(3)
      ])
    });
  }

  submitForm() {
    if (!this.paymentForm.valid) {
       this.displayMessage = "Payment Failed!";
    }
    else {
       this.displayMessage = "Payment Successful!";
    }
  }
}
