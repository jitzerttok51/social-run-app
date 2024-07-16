import { Component, input, model } from '@angular/core';
import { CommonModule } from '@angular/common'
import { MatFormFieldModule } from '@angular/material/form-field'
import { MatInputModule } from '@angular/material/input'
import { MatDatepickerModule } from '@angular/material/datepicker'
import { FormsModule } from '@angular/forms'
import { MAT_DATE_LOCALE, provideNativeDateAdapter } from '@angular/material/core';

@Component({
    selector: 'app-date-picker',
    standalone: true,
    providers: [provideNativeDateAdapter(), { provide: MAT_DATE_LOCALE, useValue: 'en-GB' }],
    imports: [
      FormsModule,
      CommonModule,
      MatDatepickerModule,
      MatFormFieldModule,
      MatInputModule,
    ],
    templateUrl: './date-picker.component.html',
    styleUrl: './date-picker.component.scss'
})
export class AppDatePicker {
    displayName = input<string>('Field')
    errorMessage = input<string>()
    value = model<Date>(new Date())

    onDateChanged(date: Date) {
        console.log(date)
        this.value.set(date);
    }

    log(x:any) {console.log(x)}
}
  