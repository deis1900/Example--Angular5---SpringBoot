import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {FormGroup, NgForm, FormBuilder, Validators} from '@angular/forms';
import {Invoice} from '../invoice/invoice';
import {InvoiceService} from '../invoice/invoice.service';

@Component({
  selector: 'app-create-invoice',
  templateUrl: './create-invoice.component.html',
  styleUrls: ['./create-invoice.component.scss']
})
export class CreateInvoiceComponent implements OnInit {
  isLinear = false;
  firstFormGroup: FormGroup;
  secondFormGroup: FormGroup;
  thirdFormGroup: FormGroup;
  fourthFormGroup: FormGroup;
  invoice: Invoice;

  constructor(private invoiceService: InvoiceService, activeRoute: ActivatedRoute,
              private router: Router, private _formBuilder: FormBuilder) {
    this.editing = activeRoute.snapshot.params['mode'] === 'edit';
    const id = activeRoute.snapshot.params['id'];
    if (id != null) {
      const amount = activeRoute.snapshot.params['amount'];
      const currency = activeRoute.snapshot.params['currency'];
      const checkDate = activeRoute.snapshot.params['checkDate'];
      const customerId = activeRoute.snapshot.params['customerId'];
      const productsId = activeRoute.snapshot.params['productsId'];
      if (amount != null &&
        checkDate != null &&
        customerId != null &&
        productsId != null) {
        this.invoice.id = id;
        this.invoice.amount = amount;
        this.invoice.currency = currency;
        this.invoice.customer = customerId;
        this.invoice.checkDate = checkDate;
        this.invoice.products = productsId;
      } else {
        Object.assign(this.invoice, invoiceService.getInvoice(id)
            .subscribe(data => {
                return data;
              }
            )
          || new Invoice(undefined, undefined, undefined, undefined, undefined, undefined)
        );
      }
    }
  }

  editing = false;

  ngOnInit() {
    this.firstFormGroup = this._formBuilder.group({
      firstCtrl: ['', Validators.required]
    });
    this.secondFormGroup = this._formBuilder.group({
      secondCtrl: ['', Validators.required]
    });
    this.thirdFormGroup = this._formBuilder.group({
      thirdCtrl: ['', Validators.required]
    });
    this.fourthFormGroup = this._formBuilder.group({
      fourthCtrl: ['', Validators.required]
    });
  }

  submitForm(form: NgForm) {
    if (form.valid) {
      this.invoiceService.postInvoice(this.invoice);
      this.router.navigateByUrl('/invoice/table');
    }
  }

  resetForm() {
    this.invoice =
      new Invoice(undefined, undefined, undefined, undefined, undefined, undefined);
  }

}
