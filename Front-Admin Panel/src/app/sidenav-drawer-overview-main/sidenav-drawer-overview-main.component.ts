import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-sidenav-drawer-overview-main',
  templateUrl: './sidenav-drawer-overview-main.component.html',
  styleUrls: ['./sidenav-drawer-overview-main.component.scss']
})
export class SidenavDrawerOverviewMainComponent implements OnInit {
  panelOpenState = false;
  invoiceId: number;
  customer: number;
  invoice: number;
  prod: number;

  constructor(private router: Router) {
  }

  ngOnInit() {
  }
  goToProduct(product) {
    // create test (enter value)
      this.router.navigate(['/product', product]);
  }

  goToCustomer(customer) {
    // create test (enter value)
    this.router.navigate(['/customer', customer]);
  }
  goToInvoice(invoice) {
    // create test (enter value)
   this.router.navigate(['/invoice', invoice ]);
  }

}
