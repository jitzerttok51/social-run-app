import { Component, inject } from '@angular/core';
import { ActivatedRoute, RedirectCommand, ResolveFn, Router } from '@angular/router';
import { User, UserService } from '../../../services/user-service/user-service.service';
import { map, Observable } from 'rxjs';
import { toSignal } from '@angular/core/rxjs-interop';
import { navigateToError } from '../error-page/error-page.component';

export interface ErrorPageInput {
  statusText: string | undefined
  status: number | undefined
  message: string | undefined
}

@Component({
  selector: 'app-user-profile-page',
  standalone: true,
  imports: [],
  templateUrl: './user-profile-page.component.html',
  styleUrl: './user-profile-page.component.scss'
})
export class UserProfilePageComponent {

  activatedRoute = inject(ActivatedRoute)

  user = toSignal(this.activatedRoute.data.pipe(map(data => data['user'] as User)))
}

export const userResolver: ResolveFn<User> = (route, _) => {
  let username = route.paramMap.get('id')!
  const router = inject(Router)
  return inject(UserService)
    .getUser(username)
    .pipe(map(status => {
      if(!status.ok) {
        return navigateToError(status, router);
      }
      return status.body
    }));
}