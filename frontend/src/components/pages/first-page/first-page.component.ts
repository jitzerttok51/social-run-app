import { Component } from '@angular/core';
import { MatCardModule } from '@angular/material/card'
import { MatFormFieldModule } from '@angular/material/form-field'
import { MatCheckboxModule } from '@angular/material/checkbox'
import { MatRadioModule } from '@angular/material/radio'
import { MatInputModule } from '@angular/material/input'
import { MatButtonModule } from '@angular/material/button'
import { MatDividerModule } from '@angular/material/divider'
import { MatDatepickerModule } from '@angular/material/datepicker'
import {provideNativeDateAdapter, MAT_DATE_LOCALE} from '@angular/material/core';

@Component({
  selector: 'first-page',
  standalone: true,
  providers: [provideNativeDateAdapter(), { provide: MAT_DATE_LOCALE, useValue: 'en-GB' }],
  imports: [MatCardModule, MatDatepickerModule, MatDividerModule, MatFormFieldModule, MatInputModule, MatButtonModule, MatRadioModule, MatCheckboxModule],
  templateUrl: './first-page.component.html',
  styleUrl: './first-page.component.scss'
})
export class FirstPage {

}
