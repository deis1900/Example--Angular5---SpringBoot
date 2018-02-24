import {Component, OnInit} from '@angular/core';
import {Product} from './product/product';
import {ProductsService} from './product/product.service';

@Component({
  selector: 'app-products-page',
  templateUrl: './products-page.component.html',
  styleUrls: ['./products-page.component.scss'],

})
export class ProductsPageComponent implements OnInit {

  products: Product[];
  isMarked = false;
  searchType = '';
  constructor(private productService: ProductsService) { }

  ngOnInit() {
    this.productService.getProducts().subscribe(list => {
      this.products = list;
    });
  }

  onClick() {
    this.isMarked = true;
  }
}
