import {Injectable} from '@angular/core';
import {Product} from './product';
import {HttpClient, HttpErrorResponse, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {ErrorObservable} from 'rxjs/observable/ErrorObservable';
import {catchError, map, retry} from 'rxjs/operators';

@Injectable()
export class ProductsService {
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      // 'Authorization': 'my-auth-token'
    })
  };

  constructor(private http: HttpClient) {
  }
  getProducts(): Observable<Product[]> {
    return this.http.get('/product', this.httpOptions)
      .pipe(
        map(
      data => {
        return data['listProducts'];
      }
    ),
      retry(3),
      catchError(this.handleError)
  );
  }
  postProduct(product: Product): Observable<Product> {
    return this.http.post<Product>('/product/save', product, this.httpOptions)
      .pipe(
        catchError(this.handleError)
      );
  }

  updateProduct(product: Product): Observable<Product> {
    return this.http.put<Product>(`/product/${product.id}`, product, this.httpOptions)
      .pipe(
        catchError(this.handleError)
      );
  }
  deleteProduct(id: number): Observable<{}> {
    return this.http.delete(`/product/${id}`, this.httpOptions)
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



