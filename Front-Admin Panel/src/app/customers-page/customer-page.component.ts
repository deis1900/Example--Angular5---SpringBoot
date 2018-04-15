import { Component, OnInit } from '@angular/core';
import {Customer} from './customer/customer';
import {CustomerService} from './customer/cusotmer.service';


@Component({
  selector: 'app-customer-page',
  templateUrl: './customer-page.component.html',
  styleUrls: ['./customer-page.component.scss'],
})
export class CustomerPageComponent implements OnInit {

  customers: Customer[] = [];
  isMarked = false;
  searchUserName = '';
  constructor(private customerService: CustomerService) { }

  ngOnInit() {
    this.customerService.getUsers().subscribe(list => {
      this.customers = list;
    });
  }

  onClick() {
    this.isMarked = true;
  }

}
