///<reference path="customers-page/customer/customer.component.ts"/>
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
  MatSidenavModule,
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
import { LoginPageComponent } from './user/login-page/login-page.component';

const appRoutes: Routes = [
  {
    path: '', component: HomePageComponent
  },
  {
    path: 'product', component: ProductsPageComponent
  },
  {path: 'product/table/:mode/:id', component: AddProductComponent},
  {path: 'product/table/:mode', component: AddProductComponent},
  {path: 'product/table', component: ProductTableComponent},
  {path: 'product/:id', component: ProductComponent},
  {
    path: 'customer', component: CustomerPageComponent
  },
  {path: 'customer/table/:mode/:id', component: RegistrationComponent},
  {path: 'customer/table/:mode', component: RegistrationComponent},
  {path: 'customer/table', component: CustomerTableComponent},
  {path: 'customer/:email', component: CustomerComponent},
  {
    path: 'invoice', component: InvoicesPageComponent
  },
  {path: 'invoice/table/:mode/:id', component: CreateInvoiceComponent},
  {path: 'invoice/table/:mode', component: CreateInvoiceComponent},
  {path: 'invoice/table', component: InvoiceTableComponent},
  {path: 'invoice/:id', component: InvoiceComponent},
  {
    path: 'file/upload', component: FileUploaderComponent
  },
  {
    path: '**/*', redirectTo: '/'
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
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes),
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
  ],
  providers: [InvoiceService, ProductsService, CustomerService, FileUploaderService,
    {provide: MatStepperIntl, useClass: CreateInvoiceComponent}],
  bootstrap: [AppComponent]
})
export class AppModule {
}
