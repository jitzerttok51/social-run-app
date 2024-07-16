import { Component, effect, input, model, OnInit, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common'
import { MatFormFieldModule } from '@angular/material/form-field'
import { MatInputModule } from '@angular/material/input'
import { AbstractControl, FormControl, FormGroupDirective, FormsModule, NgForm, ReactiveFormsModule, Validators } from '@angular/forms'
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

    required = input(false)
    control: FormControl = new FormControl('')
    displayName = input<string>('Field')
    type = input<string>('text')
    errorMessage = input<string | undefined>('')
    value = model<string>("")
    errMatcher = this

    constructor() {
        effect(() => {
            if(!!this.errorMessage()) {
                this.control.setErrors({error: true}, {emitEvent: true})
            }
        })
        effect(() => {if(this.required()) this.control.addValidators(Validators.required)})
    }

    setVal(val: any) {
        this.value.set(val.target.value);
    }
}
  