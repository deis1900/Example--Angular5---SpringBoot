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
  products: Product[] = [];
  isMarked = false;
  step = 0;
  searchInvoice = '';
  constructor(private invoiceService: InvoiceService) {
  }
  ngOnInit() {
    this.invoices = this.invoiceService.getInvoice();
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
