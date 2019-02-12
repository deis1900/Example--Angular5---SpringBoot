import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {Injectable} from '@angular/core';
import {AuthUser} from '../user/AuthUser';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private authUser: AuthUser) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    console.log(this.authUser.isAuthenticated  + ' is Authenticated');
    console.log(this.authUser.getToken());
    const cloneRequest = req.clone({
      headers: req.headers.set('X-Auth-Token',
        this.authUser.isAuthenticated ? this.authUser.getToken() :
          'anonymous:anonymous')
    });
    return next.handle(cloneRequest);
  }
}
