import { CommonModule } from "@angular/common";
import { Component, input, model } from "@angular/core";
import { FormControl, ReactiveFormsModule } from "@angular/forms";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatInputModule } from "@angular/material/input";
import { MatRadioModule } from "@angular/material/radio";
import { RadioOption } from "../../../models/RadioOption.model";

@Component({
    selector: 'app-select-option',
    standalone: true,
    providers: [],
    imports: [
      ReactiveFormsModule,
      CommonModule,
      MatFormFieldModule,
      MatInputModule,
      MatRadioModule
    ],
    templateUrl: './app-select-option.component.html'
})
export class AppSelectOption {
    radioOptions = input<RadioOption[]>([])
    value = model<string>()
    errorMessage = input<string | undefined>("Test")

    control: FormControl = new FormControl('')
}