import {Pipe, PipeTransform} from '@angular/core';
import {Customer} from './customer/customer';

@Pipe ({
  name: 'searchCustomer'
})
export class SearchCustomerPipe implements PipeTransform {

  transform(customers, value): Customer [] {
    return customers.filter(user => {
      return user.userName.includes(value);
    });
  }
}
