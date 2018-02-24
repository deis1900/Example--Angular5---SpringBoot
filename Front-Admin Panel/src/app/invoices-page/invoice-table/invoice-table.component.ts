import {Component, ViewChild} from '@angular/core';
import {MatPaginator, MatSort, MatTableDataSource} from '@angular/material';
import {InvoiceService} from '../invoice/invoice.service';
import {Invoice} from '../invoice/invoice';

@Component({
  selector: 'app-invoice-table',
  templateUrl: './invoice-table.component.html',
  styleUrls: ['./invoice-table.component.scss']
})
export class InvoiceTableComponent {
  displayedColumns ;
  dataSource: MatTableDataSource<Invoice>;
  invoices: Invoice[];

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private invoiceService: InvoiceService) {
    this.invoiceService.getInvoices()
      .subscribe(list => {
      if (!list) {
        return alert('No access to the server');
      } else {
        this.invoices = list;
        this.displayedColumns = [
          'id',
          'amount',
          'currency',
          'checkDate',
          'customer.userName',
          'customer.firstName',
          'customer.lastName',
          'customer.email',
          'customer.sex',
          'products.product[id]',
          'products.product[typeOfClothes]',
          'products.product[material]',
          'products.product[size]',
          'products.product[color]',
          'products.product[image]'
        ];
        this.dataSource = new MatTableDataSource(this.invoices);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
      }
    }
    );
  }

  applyFilter(filterValue: string) {
    filterValue = filterValue.trim();
    filterValue = filterValue.toLowerCase();
    this.dataSource.filter = filterValue;
  }

  deleteInvoice(id) {
    this.invoiceService.deleteInvoice(id).subscribe();
  }

  updateInvoice(invoice) {
    this.invoiceService.updateInvoice(invoice).subscribe();
  }
}
