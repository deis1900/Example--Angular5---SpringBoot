import {HttpClient, HttpErrorResponse, HttpHeaders} from '@angular/common/http';
import {User} from './user';
import {Injectable} from '@angular/core';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import {Observable} from 'rxjs/Observable';
import {catchError, map, retry} from 'rxjs/operators';
import {ErrorObservable} from 'rxjs/observable/ErrorObservable';


@Injectable()
export class UsersService {
  user: User;
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      // 'Authorization': 'my-auth-token'
    })
  };

  constructor(private http: HttpClient) {
  }
  getUser(userName: string): Observable<User> {
    return this.http.get(`/customer/username/${userName}`, this.httpOptions)
      .pipe(
        map(
          data => {
            const user = data;
            return user;
          }
        ),
        retry(3),
        catchError(this.handleError)
      );
  }

  getUsers(): Observable<User []> {
    return this.http.get(`/customer`, this.httpOptions)
      .pipe(
        map(
          data => {
            return data['listCustomers'];
          }
        ),
        retry(3),
        catchError(this.handleError)
      );
  }
  postUser(user: User): Observable<User> {
    return this.http.post<User>(`/customer?body=`, user, this.httpOptions)
      .pipe(
        map( data => {
          const customer = data;
          return customer;
        }),
        catchError(this.handleError)
      );
  }

  updateUser(user: User): Observable<User> {
    return this.http.put<User>(`/customer/${user.id}`, user, this.httpOptions)
      .pipe(
        map(data => {
          const customer = data;
          return customer;
        }),
        catchError(this.handleError)
      );
  }
  deleteUser(id: number): Observable<{}> {
    return this.http.delete(`/customer/${id}`, this.httpOptions)
      .pipe(
        catchError(this.handleError)
      );
  }
  private handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      console.error('An error occurred:', error.error.message);
    } else {
      console.error(
        `Backend returned code ${error.status}, ` +
        `body was: ${error.error}`);
    }
    return new ErrorObservable(
      'Something bad happened; please try again later.');
  }
}
