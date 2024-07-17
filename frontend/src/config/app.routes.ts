import { Routes } from '@angular/router';
import { RegisterPage } from '../components/pages/register-page/register-page.component';
import { LoginPage } from '../components/pages/login-page/login-page.component';
import { UserProfilePageComponent, userResolver } from '../components/pages/user-profile-page/user-profile-page.component';

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
        resolve: { user: userResolver }
    }
];
