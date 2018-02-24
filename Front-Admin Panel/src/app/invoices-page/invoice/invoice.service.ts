import {Invoice} from './invoice';
import {HttpClient, HttpErrorResponse, HttpHeaders} from '@angular/common/http';
import {ErrorObservable} from 'rxjs/observable/ErrorObservable';
import {catchError, map, retry} from 'rxjs/operators';
import {Observable} from 'rxjs/Observable';
import {Injectable} from '@angular/core';


@Injectable()
export class InvoiceService {
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      // 'Authorization': 'my-auth-token'
    })
  };

  constructor(private http: HttpClient) {
  }

  getInvoice(id: number): Observable<Invoice> {
    return this.http.get(`/invoice/${id}`, this.httpOptions)
      .pipe(
        catchError(this.handleError)
      );
  }

  getInvoices(): Observable<Invoice[]> {
    return this.http.get('/invoice', this.httpOptions)
      .pipe(
        map(
          data => {
            return data['listInvoice'];
          }
        ),
        retry(3),
        catchError(this.handleError)
      );
  }

  postInvoice(invoice: Invoice): Observable<Invoice> {
    return this.http.post<Invoice>('/invoice', invoice, this.httpOptions)
      .pipe(
        catchError(this.handleError)
      );
  }

  updateInvoice(invoice: Invoice): Observable<Invoice> {
    return this.http.put<Invoice>(`/invoice/${invoice.id}`, invoice, this.httpOptions)
      .pipe(
        catchError(this.handleError)
      );
  }

  deleteInvoice(id: number): Observable<{}> {
    return this.http.delete(`/invoice/${id}`, this.httpOptions)
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
