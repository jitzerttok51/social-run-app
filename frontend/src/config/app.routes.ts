import { Routes } from '@angular/router';
import { RegisterPage } from '../components/pages/register-page/register-page.component';
import { SecondPage } from '../components/pages/second-page/second-page.component';

export const routes: Routes = [
    {
        component: RegisterPage,
        path: 'register-page'
    },
    {
        component: SecondPage,
        path: 'second-page'
    }
];
