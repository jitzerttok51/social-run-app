import { AfterViewInit, Component, effect, inject, ViewChild } from '@angular/core';
import { RouterOutlet, RouterLink, RouterLinkActive } from '@angular/router';
import { MatToolbarModule } from '@angular/material/toolbar'
import { MatButtonModule } from '@angular/material/button'
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { AppErrorService } from '../../services/app-error/app-error-service.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, MatToolbarModule, MatButtonModule, RouterLink, RouterLinkActive, MatSnackBarModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent implements AfterViewInit {

  appError = inject(AppErrorService)

  ngAfterViewInit(): void {
    console.log(this.link)
    this.link.focus()
  }

  title = 'frontend';

  @ViewChild("test") link!: HTMLAnchorElement
  ngOnInit(): void {

  }

  constructor(private snackBar: MatSnackBar) {
    effect(() =>  {
      let msg = this.appError.error()
      if(msg) {
        snackBar.open(this.appError.error(), 'Dismiss')
        this.appError.printErrorMessage('')
      }
    }, {allowSignalWrites: true})
  }
  
}
