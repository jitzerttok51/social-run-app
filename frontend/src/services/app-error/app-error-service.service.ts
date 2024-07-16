import { computed, Injectable, signal } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AppErrorService {

  protected _error = signal<string>('')

  readonly error = computed(() => this._error())

  constructor() { }

  printErrorMessage(message: string) {
    this._error.set(message)
  }
}
