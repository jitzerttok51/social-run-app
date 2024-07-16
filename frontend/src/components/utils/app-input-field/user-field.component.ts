import { Component, effect, input, model, OnInit, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common'
import { MatFormFieldModule } from '@angular/material/form-field'
import { MatInputModule } from '@angular/material/input'
import { AbstractControl, FormControl, FormGroupDirective, FormsModule, NgForm, ReactiveFormsModule } from '@angular/forms'
import { ErrorStateMatcher } from '@angular/material/core';

@Component({
    selector: 'app-input-field',
    standalone: true,
    providers: [],
    imports: [
      ReactiveFormsModule,
      CommonModule,
      MatFormFieldModule,
      MatInputModule
    ],
    templateUrl: './user-field.component.html',
    styleUrl: './user-field.component.scss'
})
export class AppInputField {

    control: FormControl = new FormControl('')
    displayName = input<string>('Field')
    type = input<string>('text')
    errorMessage = input<string | undefined>("Test")
    value = model<string>("")
    errMatcher = this

    constructor() {
        effect(() => {
            if(!!this.errorMessage()) {
                this.control.setErrors({error: true}, {emitEvent: true})
            }
        })
    }

    setVal(val: any) {
        this.value.set(val.target.value);
    }
}
  