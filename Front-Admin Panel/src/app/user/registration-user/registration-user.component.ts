import { Component} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-registration-user',
  templateUrl: './registration-user.component.html',
  styleUrls: ['./registration-user.component.scss']
})
export class RegistrationUserComponent  {
  myForm: FormGroup;

  constructor(private formBuilder: FormBuilder, ) {

    this.myForm = new FormGroup(
      {
        'userName': new FormControl('Tom', Validators.required),
        'userEmail': new FormControl('', [Validators.required,
          Validators.pattern('[a-zA-Z_]+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}')
        ]),
        'userPhone': new FormControl()
      });
  }

  submit() {
    console.log(this.myForm);
  }

}
