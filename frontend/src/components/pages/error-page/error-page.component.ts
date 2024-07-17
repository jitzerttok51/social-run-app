import { Component, computed, inject, Signal } from '@angular/core';
import { toSignal } from '@angular/core/rxjs-interop';
import { ActivatedRoute, ActivatedRouteSnapshot, ParamMap, RedirectCommand, Router } from '@angular/router';
import { map } from 'rxjs';
import { ErrorPageInput } from '../user-profile-page/user-profile-page.component';
import { Status } from '../../../models/Status.model';

@Component({
  selector: 'app-error-page',
  standalone: true,
  imports: [],
  templateUrl: './error-page.component.html',
  styleUrl: './error-page.component.scss'
})
export class ErrorPageComponent {
  activatedRoute = inject(ActivatedRoute)

  paramsMap = this.activatedRoute.snapshot.queryParamMap
  errorMessage = this.getParam('message', this.paramsMap, 'Server Error')
  errorType = this.getParam('statusText', this.paramsMap, 'NotFound')
  errorCode = this.getParam('status', this.paramsMap, '404')

  getParam(name: string, paramsMap: ParamMap | undefined, def: string) {
    if(!paramsMap) {
      return def
    }
    const value = paramsMap.get(name)
    return value ? value : def
  }
}

export function navigateToError(status: Status<any>, router: Router) {
  let queryParams: any = {message: status.message}
  if(status.errResponse) {
    queryParams = {
      statusText: status.errResponse.statusText, 
      status: (''+status.errResponse.status), 
      message: status.errResponse.message
    }
  }
  return new RedirectCommand(router.createUrlTree(['/error'], {queryParams: queryParams}))
}