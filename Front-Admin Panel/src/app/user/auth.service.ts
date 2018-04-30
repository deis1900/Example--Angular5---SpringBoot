import {Injectable} from '@angular/core';
import {Router} from '@angular/router';
import {HttpClient} from '@angular/common/http';

@Injectable()
export class AuthService {
  private _isAuthenticated = false;

  get isAuthenticated() {
    return this._isAuthenticated;
  }

  set isAuthenticated(value) {

    this._isAuthenticated = value;
  }

  constructor(
    private httpClient: HttpClient,
    private router: Router
  ) {
  }

  logOut() {
    this.isAuthenticated = false;
    this.router.navigate(['/login']);
  }

}
