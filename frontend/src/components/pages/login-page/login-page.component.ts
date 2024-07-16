import { CommonModule } from '@angular/common';
import { Component, computed, effect, inject, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { AppInputField } from "../../utils/app-input-field/user-field.component";
import { MatDividerModule } from '@angular/material/divider';
import { MatButtonModule } from '@angular/material/button';
import { AppErrorService } from '../../../services/app-error/app-error-service.service';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
@Component({
  selector: 'login-page',
  standalone: true,
  imports: [CommonModule, MatCardModule, FormsModule, AppInputField, MatDividerModule, MatButtonModule],
  templateUrl: './login-page.component.html',
  styleUrl: './login-page.component.scss'
})
export class LoginPage {

  appError = inject(AppErrorService)
  router = inject(Router)
  snackbar = inject(MatSnackBar)

  username = signal('')
  password = signal('')

  isReady = computed(() => this.username() && this.password())

  isLoading = signal(false)

  constructor() {
  }

  onSubmit() {
    if(!this.isReady()) {
      this.appError.printErrorMessage("Please fill all required fields");
      return;
    }

    this.isLoading.set(true)
    setTimeout(() => {
       this.snackbar.open("Success", 'Dismiss')._dismissAfter(3000)
       this.isLoading.set(false);
       this.username.set('')
       this.password.set('')
       setTimeout(() => this.router.navigateByUrl('/'), 1000);
    }, 3000)
  }

}
