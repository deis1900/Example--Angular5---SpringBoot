import {User} from './user';
import {UsersService} from './user.service';
import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Subscription} from 'rxjs/Subscription';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss'],
  providers: [UsersService],
})


export class UserComponent implements OnInit {
  email: string;
  private routeSubscription: Subscription;
  customer: User;

  constructor(private usersService: UsersService, private route: ActivatedRoute) {
    this.routeSubscription = route.params.subscribe(params =>
      this.email = params['email']);
  }

  ngOnInit() {
    this.findCustomer(this.email);
  }

  findCustomer(searchEmail: string): any {
    return this.usersService.getUser(searchEmail).subscribe(data => {
        return this.customer = data;
      }
    );
  }
}
