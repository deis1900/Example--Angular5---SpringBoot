import {Customer} from './customer';
import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Subscription} from 'rxjs/Subscription';
import {CustomerService} from './cusotmer.service';

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.scss'],
  providers: [CustomerService],
})


export class CustomerComponent implements OnInit {
  email: string;
  private routeSubscription: Subscription;
  customer: Customer;

  constructor(private customerService: CustomerService, private route: ActivatedRoute) {
    this.routeSubscription = route.params.subscribe(params =>
      this.email = params['email']);
  }

  ngOnInit() {
    this.findCustomer(this.email);
  }

  findCustomer(searchEmail: string): any {
    return this.customerService.getUser(searchEmail).subscribe(data => {
        return this.customer = data;
      }
    );
  }
}
