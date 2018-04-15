import {Customer} from '../../customers-page/customer/customer';
import {Product} from '../../products-page/product/product';

export class Invoice {
  constructor(
    public amount: number,
    public currency: String,
    public customer: Customer,
    public products: Product[],
    public checkDate?: number,
    public id?: number) {
  }
}
