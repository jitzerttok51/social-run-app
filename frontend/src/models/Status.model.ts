export class Status<T> {
    constructor(readonly ok: boolean, readonly message: string, readonly body: T) {}
}
