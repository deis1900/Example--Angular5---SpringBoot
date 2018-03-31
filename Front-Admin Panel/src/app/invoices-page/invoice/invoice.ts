import {User} from '../../customers-page/user/user';
import {Product} from '../../products-page/product/product';

export class Invoice {
  constructor(
    public amount: number,
    public currency: String,
    public customer: User,
    public products: Product[],
    public checkDate?: number,
    public id?: number) {
  }
}
