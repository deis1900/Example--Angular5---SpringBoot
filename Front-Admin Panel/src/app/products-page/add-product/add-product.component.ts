import {Component} from '@angular/core';
import {NgForm} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {ProductsService} from '../product/product.service';
import {Product} from '../product/product';

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.scss']
})
export class AddProductComponent {
  product: Product = new Product(null, '', '', '', '', null, '');
  editing = false;
  products: Product [];

  constructor(private productService: ProductsService,
              private activeRoute: ActivatedRoute,
              private router: Router) {
    this.editing = activeRoute.snapshot.params['mode'] === 'edit';
    const id = activeRoute.snapshot.params['id'];
    if (id != null) {
      const typeOfClothes = activeRoute.snapshot.params['typeOfClothes'];
      const material = activeRoute.snapshot.params['material'];
      const size = activeRoute.snapshot.params['size'];
      const color = activeRoute.snapshot.params['color'];
      const dateOfLastChange = activeRoute.snapshot.params['dateOfLastChange'];
      const image = activeRoute.snapshot.params['image'];

      if (typeOfClothes != null &&
        material != null &&
        size != null &&
        color != null &&
        dateOfLastChange != null &&
        image != null) {
        this.product.id = id;
        this.product.typeOfClothes = typeOfClothes;
        this.product.material = material;
        this.product.size = size;
        this.product.color = color;
        this.product.dateOfLastChange = dateOfLastChange;
        this.product.image = image;
      } else {
        Object.assign(this.product, productService.getProducts()
          .map(data => {
              const prod = data ['listProducts'];
              return prod.filter(item => item.id === id)
                ||
                new Product(null, '', '', '', '', null, '');
            }
          )
        );
      }
    }
  }

  submitForm(form: NgForm) {
    if (form.valid) {
      if (this.editing === true) {
        this.productService.updateProduct(this.product).subscribe(product => this.products.push(product));
      } else {
        this.productService.postProduct(this.product).subscribe(product => this.products.push(product));
      }
      this.router.navigateByUrl('/product/table');
    }
  }

  resetForm() {
    this.product =
      new Product(null, '', '', '', '', null, '');
  }
}

