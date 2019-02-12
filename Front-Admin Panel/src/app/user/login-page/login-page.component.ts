import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Subscription} from 'rxjs/Subscription';
import {ActivatedRoute, Router} from '@angular/router';
import {AuthService} from '../auth.service';
import {MatSnackBar, MatSnackBarConfig} from '@angular/material';
import {AlertService} from '../../directives/alert.service';
import {User} from '../User';
import {AuthUser} from '../AuthUser';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss']
})
export class LoginPageComponent implements OnInit, OnDestroy {
  submitted = false;
  loading = false;
  returnUrl: string;
  user: FormGroup;
  snackbarSubscription: Subscription;
  serviceSubscrition: Subscription;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private authService: AuthService,
    private formBuilder: FormBuilder,
    private snackBar: MatSnackBar,
    private alertService: AlertService,
    private authUser: AuthUser
  ) {
  }

  ngOnInit() {
    this.authUser.isAuthenticated = false;
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';

    this.user = this.formBuilder.group({
      account: this.formBuilder.group({
        username: ['', [Validators.required, Validators.maxLength(15), Validators.minLength(3)]],
        password: ['', [Validators.required, Validators.maxLength(64), Validators.minLength(5)]],
        rememberMe: [false]
      })
    });
  }

  login() {
    this.loading = true;
    const userForm: User = {
      username: this.user.get('account.username').value,
      password: this.user.get('account.password').value
    };
    this.authService.login(userForm)
      .subscribe(
        () => {
          this.router.navigate([this.returnUrl]);
        },
        error => {
          this.alertService.error(error);
          this.loading = false;
        });
  }

  onSubmit(): void {
    this.submitted = true;
    this.loading = true;
    const snackbarConfig: MatSnackBarConfig = {
      duration: 5000
    };
    this.login();
    const snackBarAction = this.snackBar.open(`Authentication in progress ... `, 'X', snackbarConfig);
    this.snackbarSubscription = snackBarAction.afterDismissed()
      .subscribe(() => {
        this.submitted = false;
        this.loading = false;
        this.router.navigate(['/']);
      });
  }

  ngOnDestroy() {
    if (this.snackbarSubscription) {
      this.snackbarSubscription.unsubscribe();
    }
    if (this.serviceSubscrition) {
      this.serviceSubscrition.unsubscribe();
    }
  }

}
