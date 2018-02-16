import { Component, OnInit } from '@angular/core';
import {User} from './user/user';
import {UsersService} from './user/user.service';

@Component({
  selector: 'app-customer-page',
  templateUrl: './customer-page.component.html',
  styleUrls: ['./customer-page.component.scss'],
})
export class CustomerPageComponent implements OnInit {

  customers: User[] = [];
  isMarked = false;
  searchUserName = '';
  constructor(private userService: UsersService) { }

  ngOnInit() {
    this.userService.getUsers().subscribe(list => {
      this.customers = list;
    });
  }

  onClick() {
    this.isMarked = true;
  }

}
