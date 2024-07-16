import { Component, effect, inject, signal, WritableSignal } from '@angular/core';
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
import { ReactiveFormsModule } from '@angular/forms'
import { UserService } from '../../../services/user-service/user-service.service';
import { AppInputField } from "../../utils/app-input-field/user-field.component";
import { AppDatePicker } from "../../utils/app-date-picker/date-picker.component";
import { mergeToObject, UserRegister } from '../../../models/UserRegister.model';
import { combineLatest, debounceTime, delay, map, merge, Subscription, switchMap, tap, zip } from 'rxjs'
import { toObservable } from '@angular/core/rxjs-interop';
import { AppSelectOption } from "../../utils/app-select-option/app-select-option.component";
import { RadioOption } from '../../../models/RadioOption.model';
import { AppErrorService } from '../../../services/app-error/app-error-service.service';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';

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
    ReactiveFormsModule,
    AppInputField,
    AppDatePicker,
    AppSelectOption,
    MatProgressSpinnerModule
],
  templateUrl: './first-page.component.html',
  styleUrl: './first-page.component.scss'
})
export class FirstPage {
  service = inject(UserService)
  appError = inject(AppErrorService)

  username = signal<string>("")
  email = signal<string>("")
  firstName = signal<string>("")
  lastName = signal<string>("")
  password = signal<string>("")
  confirmPassword = signal<string>("")
  birthday = signal<Date>(new Date())
  gender = signal<'MALE' | 'FEMALE'>('MALE')
  errors = signal(true)
  termsAndConditions = signal(false)
  isLoading = signal(false)

  usernameError = signal<string | undefined>(undefined)
  emailError = signal<string | undefined>(undefined)
  firstNameError = signal<string | undefined>(undefined)
  lastNameError = signal<string | undefined>(undefined)
  passwordError = signal<string | undefined>(undefined)
  confirmPasswordError = signal<string | undefined>(undefined)
  birthdayError = signal<string | undefined>(undefined)
  genderError = signal<string | undefined>(undefined)

  subs$: Subscription = combineLatest([
      toObservable(this.username),
      toObservable(this.email),
      toObservable(this.firstName),
      toObservable(this.lastName),
      toObservable(this.password),
      toObservable(this.confirmPassword),
      toObservable(this.birthday),
      toObservable(this.gender)
    ]).pipe(
      map(mergeToObject),
      tap(x => console.log(x)),
      debounceTime(1000),
      switchMap(x => this.service.validateRegistrationForm(x)))
      .subscribe(x => {
        console.log(x)
        console.log(x.isError())
        this.errors.set(x.isError())
        this.emailError.set(x.email)
        this.usernameError.set(x.username)
        this.passwordError.set(x.password)
        this.confirmPasswordError.set(x.confirmPassword)
        this.firstNameError.set(x.firstName)
        this.lastNameError.set(x.lastName)
        this.birthdayError.set(x.dateOfBirth)
      })

  readonly radioOptions: RadioOption[] = [
    new RadioOption('MALE', 'Male'), 
    new RadioOption('FEMALE', 'Female')
  ]

  onSubmit() {
    if(this.errors()) {
      this.appError.printErrorMessage("Please fill all fields correctly")
      return;
    }
    if(!this.termsAndConditions()) {
      this.appError.printErrorMessage("Please accept the terms and conditions to continue")
      return;
    }
    this.isLoading.set(true)
    setTimeout(()=> {
      this.isLoading.set(false)
      this.appError.printErrorMessage("Success")
    }, 5000)
  }

  log(x:any) {console.log(x)}
}
