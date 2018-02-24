import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Subscription} from 'rxjs/Subscription';
import {Invoice} from './invoice';
import {InvoiceService} from './invoice.service';
import {ProductsService} from '../../products-page/product/product.service';

@Component({
  selector: 'app-invoice',
  templateUrl: './invoice.component.html',
  styleUrls: ['./invoice.component.scss'],
  providers: [ProductsService],
})
export class InvoiceComponent implements OnInit {
  id: number;
  private routeSubscription: Subscription;
  invoice: Invoice;


  constructor(private route: ActivatedRoute, private invoiceService: InvoiceService) {
    this.routeSubscription = route.params.subscribe(params =>
      this.id = params['id']);
  }

  ngOnInit() {
    this.invoiceService.getInvoice(this.id).subscribe(data => {
      this.invoice = data;
    });
  }

}
