import {Component, ViewChild} from '@angular/core';
import {MatPaginator, MatSort, MatTableDataSource} from '@angular/material';
import {User} from '../user/user';
import {UsersService} from '../user/user.service';

@Component({
  selector: 'app-customer-table',
  templateUrl: './customer-table.component.html',
  styleUrls: ['./customer-table.component.scss']
})
export class CustomerTableComponent {
  displayedColumns;
  dataSource;
  users: User[];

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private userService: UsersService) {
    this.userService.getUsers().subscribe(data => {
        if (!data) {
          return alert('No access to the server');
        } else {
          this.users = data;
          this.displayedColumns  = [
            'id',
            'firstName',
            'lastName',
            'userName',
            'password',
            'email',
            'sex',
            'phone',
            'access',
            'image', ];
          this.dataSource = new MatTableDataSource(this.users);
          this.dataSource.paginator = this.paginator;
          this.dataSource.sort = this.sort;
        }

      }
    );
  }

  applyFilter(filterValue: string) {
    filterValue = filterValue.trim();
    filterValue = filterValue.toLowerCase();
    this.dataSource.filter = filterValue;
  }

  deleteCustomer(key) {
    this.userService.deleteUser(key);
  }
}
