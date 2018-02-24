import {Component, OnInit} from '@angular/core';
import {InvoiceService} from './invoice/invoice.service';
import {Invoice} from './invoice/invoice';
import {Product} from '../products-page/product/product';

@Component({
  selector: 'app-invoices-page',
  templateUrl: './invoices-page.component.html',
  styleUrls: ['./invoices-page.component.scss']
})
export class InvoicesPageComponent implements OnInit {
  invoices: Invoice[];
  isMarked = false;
  step = 0;
  searchInvoice = '';
  constructor(private invoiceService: InvoiceService) {
  }
  ngOnInit() {
    this.getInvoiceList();
  }
  getInvoiceList(): Invoice [] {
  this.invoiceService.getInvoices().subscribe(list => {
    this.invoices = list;
    console.log(this.invoices);
  });
    return this.invoices;
  }

  setStep(index: number) {
    this.step = index;
  }

  nextStep() {
    this.step++;
  }

  prevStep() {
    this.step--;
  }

}
