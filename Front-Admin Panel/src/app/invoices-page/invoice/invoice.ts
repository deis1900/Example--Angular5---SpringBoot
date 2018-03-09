import {User} from '../../customers-page/user/user';
import {Product} from '../../products-page/product/product';

export class Invoice {
  constructor(public id: number,
  public amount: number,
  public checkDate: number,
  public currency: String,
  public customer: User,
  public products: Product[]) {
  }
}
