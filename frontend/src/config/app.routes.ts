import { Routes } from '@angular/router';
import { FirstPage } from '../components/pages/first-page/first-page.component';
import { SecondPage } from '../components/pages/second-page/second-page.component';

export const routes: Routes = [
    {
        component: FirstPage,
        path: 'first-page'
    },
    {
        component: SecondPage,
        path: 'second-page'
    }
];
