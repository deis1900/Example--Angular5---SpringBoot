import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Subscription} from 'rxjs/Subscription';
import {Router} from '@angular/router';
import {AuthService} from '../auth.service';
import {MatSnackBar, MatSnackBarConfig} from '@angular/material';

@Component({
  selector: 'app-forget-password',
  templateUrl: './forget-password.component.html',
  styleUrls: ['./forget-password.component.scss']
})
export class ForgetPasswordComponent implements OnInit, OnDestroy {

  user: FormGroup;
  submitted = false;
  loading = false;
  serviceSubscription: Subscription;
  snackbarSubscription: Subscription;

  constructor(
    private router: Router,
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private snackBar: MatSnackBar
  ) {
  }

  ngOnInit() {
    this.user = this.formBuilder.group({
        email: ['',
          [
            Validators.required,
            Validators.pattern('/^(([^<>()\\[\\]\\\\.,;:\\s@"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@"]+)*)|' +
              '(".+"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$/')
          ]
        ]
      }
    );
  }

  onSubmit() {
    this.submitted = true;
    this.loading = true;
    const snackbarConfig: MatSnackBarConfig = {
      duration: 1700
    };

    const snackBarAction = this.snackBar.open(`Mail sent!`, 'X', snackbarConfig);
    this.snackbarSubscription = snackBarAction.afterDismissed()
      .subscribe(() => {
        this.submitted = false;
        this.loading = false;
        this.router.navigate(['/login']);
      });
  }

  ngOnDestroy() {
    if (this.serviceSubscription) {
      this.serviceSubscription.unsubscribe();
    }
    if (this.snackbarSubscription) {
      this.snackbarSubscription.unsubscribe();
    }
  }
}
