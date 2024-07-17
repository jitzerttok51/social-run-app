import { HttpErrorResponse } from "@angular/common/http";

export class Status<T> {
    errResponse?: HttpErrorResponse
    constructor(readonly ok: boolean, readonly message: string, readonly body: T) {}
}
