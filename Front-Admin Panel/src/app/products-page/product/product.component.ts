import { Component, OnInit } from '@angular/core';
import {ProductsService} from './product.service';
import {Product} from './product';
import {ActivatedRoute} from '@angular/router';
import {Subscription} from 'rxjs/Subscription';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.scss'],
  providers: [ProductsService],
})
export class ProductComponent implements OnInit {

  id: number;
  private routeSubscription: Subscription;
  product: Product;

  constructor(private route: ActivatedRoute, private productService: ProductsService) {

    this.routeSubscription = route.params.subscribe(params =>
      this.id = params['id']);
  }

  ngOnInit() {
    this.findProduct(this.id);
  }
  findProduct(searchID: number) {
    this.productService.getProducts().subscribe(data => {
      if (!data) {
        return alert('ID ' + searchID + ' is not exist'  );
      }
      return this.product = data.find(prod => prod.id === searchID);
    });
  }
}
