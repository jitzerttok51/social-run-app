import { Routes } from '@angular/router';
import { RegisterPage } from '../components/pages/register-page/register-page.component';
import { LoginPage } from '../components/pages/login-page/login-page.component';

export const routes: Routes = [
    {
        component: RegisterPage,
        path: 'register'
    },
    {
        component: LoginPage,
        path: 'login'
    }
];
