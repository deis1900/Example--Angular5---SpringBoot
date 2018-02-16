import {Pipe, PipeTransform} from '@angular/core';


@Pipe({
  name: 'searchInInvoiceUser'
})
export class SearchInvoicePipe implements PipeTransform {

  transform(invoices, value) {
    return invoices.filter(invoice => {
        return (invoice.customer.userName.includes(value));
    }
    );
  }
}
