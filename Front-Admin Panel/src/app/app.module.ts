import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {RouterModule, Routes} from '@angular/router';
import {
  MatCheckboxModule,
  MatDatepickerModule,
  MatExpansionModule,
  MatFormFieldModule,
  MatInputModule,
  MatNativeDateModule,
  MatPaginatorModule,
  MatSidenavModule, MatSnackBarModule,
  MatSortModule,
  MatStepperIntl,
  MatStepperModule,
  MatTableModule
} from '@angular/material';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import 'hammerjs';

import {AppComponent} from './app.component';
import {CustomerPageComponent} from './customers-page/customer-page.component';
import {CustomerComponent} from './customers-page/customer/customer.component';
import {RegistrationComponent} from './customers-page/registration/registration.component';
import {HoverDirective} from './hover.directive';
import {ProductComponent} from './products-page/product/product.component';
import {ProductsPageComponent} from './products-page/products-page.component';
import {HomePageComponent} from './home-page/home-page.component';
import {InvoicesPageComponent} from './invoices-page/invoices-page.component';
import {InvoiceComponent} from './invoices-page/invoice/invoice.component';
import {ProductTableComponent} from './products-page/product-table/product-table.component';
import {ProductsService} from './products-page/product/product.service';
import {SearchTypePipe} from './products-page/searchType.pipe';
import {SidenavDrawerOverviewMainComponent} from './sidenav-drawer-overview-main/sidenav-drawer-overview-main.component';
import {AddProductComponent} from './products-page/add-product/add-product.component';
import {CustomerTableComponent} from './customers-page/customer-table/customer-table.component';
import {SearchCustomerPipe} from './customers-page/searchCustomer.pipe';
import {InvoiceTableComponent} from './invoices-page/invoice-table/invoice-table.component';
import {SearchInvoicePipe} from './invoices-page/searchInvoice.pipe';
import {CreateInvoiceComponent} from './invoices-page/create-invoice/create-invoice.component';
import {InvoiceService} from './invoices-page/invoice/invoice.service';
import {FileUploaderComponent} from './file-uploader/file-uploader.component';
import {FileUploaderService} from './file-uploader/file-uploader.service';
import {CustomerService} from './customers-page/customer/cusotmer.service';
import {LoginPageComponent} from './user/login-page/login-page.component';
import {RegistrationUserComponent} from './user/registration-user/registration-user.component';
import {AuthService} from './user/auth.service';
import {ForgetPasswordComponent} from './user/forget-password/forget-password.component';
import {AuthGuard} from './guards/auth.guard';
import {AlertComponent } from './directives/alert/alert.component';
import {AlertService} from './directives/alert.service';

const appRoutes: Routes = [
  {
    path: '', component: HomePageComponent
  },
  {path: 'login', component: LoginPageComponent},
  {path: 'registration', component: RegistrationUserComponent},
  {path: 'forgot-password', component: ForgetPasswordComponent},
  {
    path: 'product', component: ProductsPageComponent
  },
  {path: 'product/table/:mode/:id', component: AddProductComponent},
  {path: 'product/table/:mode', component: AddProductComponent},
  {path: 'product/table', component: ProductTableComponent},
  {path: 'product/:id', component: ProductComponent},
  {
    path: 'customer', component: CustomerPageComponent, canActivate: [AuthGuard]
  },
  {path: 'customer/table/:mode/:id', component: RegistrationComponent, canActivate: [AuthGuard]},
  {path: 'customer/table/:mode', component: RegistrationComponent, canActivate: [AuthGuard]},
  {path: 'customer/table', component: CustomerTableComponent, canActivate: [AuthGuard]},
  {path: 'customer/:username', component: CustomerComponent, canActivate: [AuthGuard]},
  {
    path: 'invoice', component: InvoicesPageComponent, canActivate: [AuthGuard]
  },
  {path: 'invoice/table/:mode/:id', component: CreateInvoiceComponent, canActivate: [AuthGuard]},
  {path: 'invoice/table/:mode', component: CreateInvoiceComponent, canActivate: [AuthGuard]},
  {path: 'invoice/table', component: InvoiceTableComponent, canActivate: [AuthGuard]},
  {path: 'invoice/:id', component: InvoiceComponent, canActivate: [AuthGuard]},
  {
    path: 'file/upload', component: FileUploaderComponent, canActivate: [AuthGuard]
  },
  {
    path: '**', redirectTo: '/'
  }
];


@NgModule({
  declarations: [
    AppComponent,
    HomePageComponent,
    CustomerPageComponent,
    CustomerTableComponent,
    RegistrationComponent,
    CustomerComponent,
    ProductsPageComponent,
    ProductTableComponent,
    ProductComponent,
    AddProductComponent,
    InvoicesPageComponent,
    InvoiceComponent,
    SearchCustomerPipe,
    SearchTypePipe,
    HoverDirective,
    SidenavDrawerOverviewMainComponent,
    InvoiceTableComponent,
    SearchInvoicePipe,
    CreateInvoiceComponent,
    FileUploaderComponent,
    LoginPageComponent,
    RegistrationUserComponent,
    ForgetPasswordComponent,
    AlertComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes, {onSameUrlNavigation: 'reload'}),
    MatFormFieldModule,
    MatTableModule,
    MatSortModule,
    MatPaginatorModule,
    MatSidenavModule,
    MatExpansionModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatStepperModule,
    MatInputModule,
    MatCheckboxModule,
    ReactiveFormsModule,
    MatSnackBarModule
  ],
  providers: [InvoiceService, ProductsService, CustomerService, FileUploaderService, AuthService, AlertService,
    [AuthGuard],
    {provide: MatStepperIntl, useClass: CreateInvoiceComponent}],
  bootstrap: [AppComponent]
})
export class AppModule {
}
