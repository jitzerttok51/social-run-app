import { HttpClient, HttpErrorResponse, HttpEvent, HttpHandlerFn, HttpRequest } from "@angular/common/http";
import { inject, Injectable, signal } from "@angular/core";
import { Router } from "@angular/router";
import { catchError, map, Observable, of, take } from "rxjs";
import { Status } from "../../models/Status.model";
import { jwtDecode } from 'jwt-decode'
import { AuthToken } from "../../models/AuthToken.component";
import { LoginRequest } from "../../models/LoginRequest.model";

interface LoginResponse {
    accessToken: string
    expirationTime: Date
    type: string
}

@Injectable({
    providedIn: 'root'
})
export class LoginService {
    http = inject(HttpClient)
    router = inject(Router)

    private response = signal<LoginResponse | undefined>(undefined)

    private tokenInfo = signal<AuthToken | undefined>(undefined)

    get userInfo() {
        let userInfo = this.tokenInfo();
        if(!userInfo) {
            this.router.navigateByUrl('/login')
            return undefined as unknown as AuthToken
        }
        return userInfo
    }

    login(request: LoginRequest): Observable<Status<undefined>> {
        return this.http
            .post<LoginResponse>('/api/auth', request)
            .pipe(
                take(1),
                map(response => this.processResponse(response)),
                catchError((error, _) => of(this.processError(error)))
            )
    }

    private processResponse(response: LoginResponse): Status<undefined> {
        console.log(response)
        this.response.set(response);
        this.tokenInfo.set(jwtDecode(response.accessToken))
        return new Status(true, '', undefined)
    }

    private processError(error: HttpErrorResponse): Status<undefined> {
        console.log(`Error occured ${JSON.stringify(error)}`)
        return new Status(true, `Error occured ${JSON.stringify(error)}`, undefined)
    }

    static authInterceptor(req: HttpRequest<unknown>, next: HttpHandlerFn): Observable<HttpEvent<unknown>> {
        const loginService = inject(LoginService)
        const router = inject(Router)

        const authToken = loginService.response();
        if(authToken) {
            req = req
                .clone({headers: req.headers.append('Authorization', `${authToken.type} ${authToken.accessToken}`)})
        }

        return next(req)
            .pipe(catchError((error: HttpErrorResponse, obs) => {
                if(Math.floor(error.status / 100) === 4) {
                    console.log(error)
                    router.navigateByUrl('/login')
                }
                return obs
            }));
    }
}