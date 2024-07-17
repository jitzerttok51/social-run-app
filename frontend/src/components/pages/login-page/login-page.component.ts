import { CommonModule } from '@angular/common';
import { Component, computed, inject, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { AppInputField } from "../../utils/app-input-field/user-field.component";
import { MatDividerModule } from '@angular/material/divider';
import { MatButtonModule } from '@angular/material/button';
import { AppErrorService } from '../../../services/app-error/app-error-service.service';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { LoginService } from '../../../services/login-service/login-service.service';
import { UserService } from '../../../services/user-service/user-service.service';
import { LoginRequest } from '../../../models/LoginRequest.model';
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
  loginService = inject(LoginService)
  userService = inject(UserService)

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
      this.loginService
      .login(new LoginRequest(this.username(), this.password()))
      .subscribe(status => {
        this.isLoading.set(false)
        if(status.ok) {
          this.snackbar.open("Success", 'Dismiss')._dismissAfter(3000)
        } else {
          this.appError.printErrorMessage(status.message)
          return
        }
        // this.userService.getUser(this.loginService.userInfo.username).subscribe(status=> console.log(status))
        this.username.set('')
        this.password.set('')
        setTimeout(() => this.router.navigateByUrl('/'), 1000);
      })
    }, 3000)
  }

}
