import {Component, OnInit} from '@angular/core';
import {User} from '../user/user';
import {ActivatedRoute, Router} from '@angular/router';
import {UsersService} from '../user/user.service';
import {NgForm} from '@angular/forms';
import 'rxjs/add/operator/map';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss']
})
export class RegistrationComponent implements OnInit {
  customer: User;
  editing = false;
  customers: User [];
  constructor(private usersService: UsersService, activeRoute: ActivatedRoute,
              private router: Router) {
    this.editing = activeRoute.snapshot.params['mode'] === 'edit';
    const id = activeRoute.snapshot.params['id'];
    if (id != null) {
      const firstName = activeRoute.snapshot.params['firstName'];
      const lastName = activeRoute.snapshot.params['lastName'];
      const userName = activeRoute.snapshot.params['userName'];
      const password = activeRoute.snapshot.params['password'];
      const email = activeRoute.snapshot.params['email'];
      const sex = activeRoute.snapshot.params['sex'];
      const phone = activeRoute.snapshot.params['phone'];
      const access = activeRoute.snapshot.params['access'];
      const image = activeRoute.snapshot.params['image'];
      if (firstName != null &&
        lastName != null &&
        userName != null &&
        password != null &&
        email != null &&
        sex != null &&
        phone != null &&
        access != null &&
        image != null) {
        this.customer.id = id;
        this.customer.firstName = firstName;
        this.customer.lastName = lastName;
        this.customer.userName = userName;
        this.customer.password = password;
        this.customer.email = email;
        this.customer.sex = sex;
        this.customer.phone = phone;
        this.customer.access = access;
        this.customer.image = image;
      } else {
        Object.assign(this.customer, usersService.getUsers()
          .map(data => {
              const users = data['listCustomers'];
              return users.filter(item => item.id === id)
                || new User(undefined, ' ', '', '',
                  '', '', '', null, false, '');
            }
          )
        );
      }
    }
  }

  ngOnInit() {
  }
  submitForm(form: NgForm) {
    if (form.valid) {
      if (this.editing === true) {
        this.usersService.updateUser(this.customer).subscribe(user => this.customers.push(user));
      } else {
        this.usersService.postUser(this.customer).subscribe(user => this.customers.push(user));
      }
      this.router.navigateByUrl('/customer/table');
    }
  }

  resetForm() {
    this.customer =
      new User(undefined, ' ', '', '', '', '', '', 0, false, '');
  }

}
