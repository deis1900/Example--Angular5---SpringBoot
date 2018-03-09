import {Component, ViewChild} from '@angular/core';
import {Product} from '../product/product';
import {ProductsService} from '../product/product.service';
import {MatPaginator, MatSort, MatTableDataSource} from '@angular/material';
import {Router} from '@angular/router';

@Component({
  selector: 'app-product-table',
  templateUrl: './product-table.component.html',
  styleUrls: ['./product-table.component.scss'],
})
export class ProductTableComponent {
  displayedColumns ;
  dataSource: MatTableDataSource<Product>;
  products: Product[];

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private productService: ProductsService, private router: Router) {
    this.productService.getProducts().subscribe(data => {
      if (!data) {
        return alert('No access to the server');
      } else {
        this.products = data;
        this.displayedColumns = ['id', 'typeOfClothes', 'material', 'size', 'color', 'dateOfLastChange', 'image'];
        this.dataSource = new MatTableDataSource(this.products);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
      }
    });
  }

  applyFilter(filterValue: string) {
    filterValue = filterValue.trim();
    filterValue = filterValue.toLowerCase();
    this.dataSource.filter = filterValue;
  }
  deleteProduct(id: number) {
    this.productService.deleteProduct(id).subscribe();
    this.router.navigateByUrl('/product/table');
  }
}
