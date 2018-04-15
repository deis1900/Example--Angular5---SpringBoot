import {Component, ViewChild} from '@angular/core';
import {MatPaginator, MatSort, MatTableDataSource} from '@angular/material';
import {Customer} from '../customer/customer';
import {CustomerService} from '../customer/cusotmer.service';

@Component({
  selector: 'app-customer-table',
  templateUrl: './customer-table.component.html',
  styleUrls: ['./customer-table.component.scss']
})
export class CustomerTableComponent {
  displayedColumns;
  dataSource;
  users: Customer[];

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private customerService: CustomerService) {
    this.customerService.getUsers().subscribe(data => {
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
  deleteCustomer(key): void {
    this.customerService.deleteUser(key).subscribe();
  }
}
