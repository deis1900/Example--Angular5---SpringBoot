import {Pipe, PipeTransform} from '@angular/core';
import {User} from './user/user';

@Pipe ({
  name: 'searchCustomer'
})
export class SearchCustomerPipe implements PipeTransform {

  transform(customers, value): User [] {
    return customers.filter(user => {
      return user.userName.includes(value);
    });
  }
}
