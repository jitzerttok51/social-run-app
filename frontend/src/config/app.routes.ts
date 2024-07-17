import { CanActivateFn, Router, Routes } from '@angular/router';
import { RegisterPage } from '../components/pages/register-page/register-page.component';
import { LoginPage } from '../components/pages/login-page/login-page.component';
import { UserProfilePageComponent, userResolver } from '../components/pages/user-profile-page/user-profile-page.component';
import { ErrorPageComponent } from '../components/pages/error-page/error-page.component';
import { inject } from '@angular/core';
import { LoginService } from '../services/login-service/login-service.service';
import { AppErrorService } from '../services/app-error/app-error-service.service';

export const authGuard: CanActivateFn = () => {
    const router = inject(Router)
    const loginService = inject(LoginService)
    const appError = inject(AppErrorService)
    if(!loginService.isLoggedIn()) {
        appError.printErrorMessage("You need to login to acces this page")
        router.navigateByUrl('/login')
    }
    return loginService.isLoggedIn()
}

export const routes: Routes = [
    {
        component: RegisterPage,
        path: 'register'
    },
    {
        component: LoginPage,
        path: 'login'
    },
    {
        component: UserProfilePageComponent,
        path: 'users/:id',
        resolve: { user: userResolver },
        canActivate: [ authGuard ]
    },
    {
        component: ErrorPageComponent,
        path: 'error'
    }
];
