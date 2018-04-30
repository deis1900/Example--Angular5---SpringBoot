import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, NgForm, Validators} from '@angular/forms';
import {Subscription} from 'rxjs/Subscription';
import {ActivatedRoute, Router} from '@angular/router';
import {AuthService} from '../auth.service';
import {MatSnackBar, MatSnackBarConfig} from '@angular/material';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss']
})
export class LoginPageComponent implements OnInit, OnDestroy {
  submitted = false;
  loading = false;
  returnUrl = '';
  user: FormGroup;
  serviceSubscription: Subscription;
  snackbarSubscription: Subscription;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private authService: AuthService,
    private formBuilder: FormBuilder,
    private snackBar: MatSnackBar,
  ) {
  }

  ngOnInit() {
    // Get returnUrl from route parameters or default to '/'
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';

    this.user = this.formBuilder.group({
      account: this.formBuilder.group({
        username: ['', [Validators.required, Validators.pattern('[^@/]+@[^@/]+'), Validators.maxLength(15)]],
        password: ['', Validators.required],
        rememberMe: [false]
      })
    });
  }

  onSubmit(): void {
    this.submitted = true;
    this.loading = true;
    const snackbarConfig: MatSnackBarConfig = {
      duration: 5000
    };

    const snackBarAction = this.snackBar.open(`Authentication in progress ...`, 'X', snackbarConfig);
    this.snackbarSubscription = snackBarAction.afterDismissed()
      .subscribe(() => {
        this.submitted = false;
        this.loading = false;
        this.authService.isAuthenticated = true;
        this.router.navigate(['/customer/${username}']);
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
