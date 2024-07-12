import { Component } from '@angular/core';
import { CommonModule } from '@angular/common'
import { MatCardModule } from '@angular/material/card'
import { MatFormFieldModule } from '@angular/material/form-field'
import { MatCheckboxModule } from '@angular/material/checkbox'
import { MatRadioModule } from '@angular/material/radio'
import { MatInputModule } from '@angular/material/input'
import { MatButtonModule } from '@angular/material/button'
import { MatDividerModule } from '@angular/material/divider'
import { MatDatepickerModule } from '@angular/material/datepicker'
import { provideNativeDateAdapter, MAT_DATE_LOCALE } from '@angular/material/core';
import { FormGroup, FormControl, ReactiveFormsModule, Validators } from '@angular/forms'

@Component({
  selector: 'first-page',
  standalone: true,
  providers: [provideNativeDateAdapter(), { provide: MAT_DATE_LOCALE, useValue: 'en-GB' }],
  imports: [
    CommonModule,
    MatCardModule,
    MatDatepickerModule,
    MatDividerModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatRadioModule,
    MatCheckboxModule,
    ReactiveFormsModule
  ],
  templateUrl: './first-page.component.html',
  styleUrl: './first-page.component.scss'
})
export class FirstPage {
  registerForm = new FormGroup({
    username: new FormControl('', [Validators.required]),
    firstName: new FormControl('', [Validators.required]),
    lastName: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required]),
    email: new FormControl('', [Validators.required, Validators.email]),
    confirmPassword: new FormControl('', [Validators.required]),
    birthday: new FormControl(new Date(), [Validators.required]),
    gender: new FormControl<'M' | 'F'>('M', [Validators.required])
  })

  onSubmit() {
    console.log(this.registerForm.value)
  }

  hasError(name: 'username' | 'firstName' | 'lastName' | 'email' | 'password' | 'confirmPassword' | 'birthday' | 'gender') {
    return this.registerForm.controls[name].hasError('required') || this.registerForm.controls[name].hasError('email')
  }

  errorMsg(name: 'username' | 'firstName' | 'lastName' | 'email' | 'password' | 'confirmPassword' | 'birthday' | 'gender', displayName: string) {
    let c = this.registerForm.controls[name]
    if (c.hasError('required')) return `${displayName} field is required`
    if (c.hasError('email')) return `Invalid email`
    return 'Unknown error'
  }
}
