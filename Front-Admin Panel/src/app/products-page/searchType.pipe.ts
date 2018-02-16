import {Pipe, PipeTransform} from '@angular/core';

@Pipe ({
  name: 'searchProduct'
})
export class SearchTypePipe implements PipeTransform {

  transform(products, value) {
    return products.filter(product => {
      return product.typeOfClothes.includes(value);
    });
  }
}
