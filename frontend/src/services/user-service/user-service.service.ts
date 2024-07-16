import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, map, Observable, of, take } from 'rxjs';

import { ViolationResponse } from '../../models/ViolationResponse.model'
import { UserRegister } from '../../models/UserRegister.model';
import { unchangedTextChangeRange } from 'typescript';

export class FieldErrors {
  username?: string
  password?: string
  firstName?: string
  lastName?: string
  confirmPassword?: string
  dateOfBirth?: string
  gender?: string
  email?: string

  isError() {
    let obj:any = this
    for(let key of Object.keys(this)) {
      if (obj[key]) return true
    }
    return false
  }
}

export interface User {
  id: number
  firstName: string,
  lastName: string
  username: string
  email: string
  sex: string
  dateOfBirth: Date
}

export class UserResponse {
  constructor(readonly user?: User, readonly errorMessage?: string) {}
  get hasError() { return !this.errorMessage; }
}

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient ) {
    console.log(`HTTP ${http}`)
  }

  validateRegistrationForm(reg: UserRegister): Observable<FieldErrors> {
    return this.http.post<FieldErrors>("/api/users?dryRun=true", reg, {observe: 'response'})
    .pipe(
      map(x => new FieldErrors()),
      take(1),
      catchError((err: HttpErrorResponse, ob) => {
      console.log(`Response from server ${JSON.stringify(err.error)}`)
      let obj = err.error as ViolationResponse
      let result: any = new FieldErrors()
      for(let key of Object.keys(result)) {
        result[key] = obj.errors.find(y=>y.property === key)?.message
      }
      if(reg.password !== reg.confirmPassword) {
        let msg = obj.errors.find(y=>y.type === 'object')?.message
        result.password = msg
        result.confirmPassword = msg
      }
      return of(result as FieldErrors)
     }));
  }

  registerUser(reg: UserRegister): Observable<UserResponse> {
    return this.http.post<User>("/api/users", reg, {observe: 'response'})
    .pipe(
      map(x => {
        if(!x.body) {
          return new UserResponse(undefined, `Failed (${x.status}) ${x.statusText}`)
        }
        return new UserResponse(x.body, undefined);
      }),
      take(1),
      catchError((err: HttpErrorResponse, ob) => {
      console.log(`Response from server ${JSON.stringify(err.error)}`)
      return of(new UserResponse(undefined, err.error as string))
     }));
  }
}
