import { Injectable } from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {ErrorObservable} from 'rxjs/observable/ErrorObservable';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {catchError, map} from 'rxjs/operators';

@Injectable()
export class FileUploaderService {

  constructor(private http: HttpClient) {
  }

  postCustomersFile(fileToUpload: File): Observable<boolean> {
    const endpoint = 'customer/upload';
    const formData: FormData = new FormData();
    formData.append('UploadFile', fileToUpload, fileToUpload.name);
    return this.http.post(endpoint, formData)
      .pipe(
        map(() => {
          return true;
        } ),
        catchError(this.handleError)
      );
  }
  postProductsFile(fileToUpload: File): Observable<boolean> {
    const endpoint = 'product/upload';
    const formData: FormData = new FormData();
    formData.append('UploadFile', fileToUpload, fileToUpload.name);
    return this.http.post(endpoint, formData)
      .pipe(
        map(() => {
          return true;
        } ),
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
