import {AfterViewInit, Component, ViewChild} from '@angular/core';
import {MatPaginator, MatSort, MatTableDataSource} from '@angular/material';
import {InvoiceService} from '../invoice/invoice.service';
import {Invoice} from '../invoice/invoice';

@Component({
  selector: 'app-invoice-table',
  templateUrl: './invoice-table.component.html',
  styleUrls: ['./invoice-table.component.scss']
})
export class InvoiceTableComponent implements AfterViewInit {
  displayedColumns = [
    'id',
    'amount',
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
  dataSource: MatTableDataSource<Invoice>;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private invoiceService: InvoiceService) {
    const invoices: Invoice[] = this.invoiceService.getInvoice();
    this.dataSource = new MatTableDataSource(invoices);
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  applyFilter(filterValue: string) {
    filterValue = filterValue.trim();
    filterValue = filterValue.toLowerCase();
    this.dataSource.filter = filterValue;
  }

  // deleteInvoice(invoice) {
  //   this.invoiceService.deleteInvoice(invoice);
  // }
  //
  // updateInvoice(invoice) {
  //   this.invoiceService.updateInvoice(invoice);
  // }
}
