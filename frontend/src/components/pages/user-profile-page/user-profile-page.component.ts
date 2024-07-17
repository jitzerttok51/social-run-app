import { Component, inject } from '@angular/core';
import { ActivatedRoute, ResolveFn } from '@angular/router';
import { User, UserService } from '../../../services/user-service/user-service.service';
import { map, Observable } from 'rxjs';
import { toSignal } from '@angular/core/rxjs-interop';

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

export const userResolver: ResolveFn<User> = (route, state) => {
  let username = route.paramMap.get('id')!
  return inject(UserService)
    .getUser(username)
    .pipe(map(status => {
      console.log(status)
      return status.body
    }));
}