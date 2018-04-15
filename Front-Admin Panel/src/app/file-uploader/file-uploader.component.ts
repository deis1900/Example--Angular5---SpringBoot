import {ChangeDetectorRef, Component} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {FileUploaderService} from './file-uploader.service';

@Component({
  selector: 'app-file-uploader',
  templateUrl: './file-uploader.component.html',
  styleUrls: ['./file-uploader.component.scss']
})
export class FileUploaderComponent  {

  fileToUpload: File = null;
  myForm: FormGroup;

  // constructor(private fileService: FileUploaderService) {}
  formGroup = this.fb.group({
    file: [null, Validators.required]
  });
  constructor(private fb: FormBuilder, private cd: ChangeDetectorRef, private fileService: FileUploaderService) {
    this.myForm = new FormGroup(
      {
        'userName': new FormControl('Tom', Validators.required),
        'userEmail': new FormControl('', [ Validators.required,
          Validators.pattern('[a-zA-Z_]+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}')
      ]),
      'userPhone': new FormControl()
    });
  }

  submit() {
    console.log(this.myForm);
  }

  onFileChange(event) {
    const reader = new FileReader();

    if (event.target.files && event.target.files.length) {
      const [file] = event.target.files;
      reader.readAsDataURL(file);

      reader.onload = () => {
        this.formGroup.patchValue({
          file: reader. result
        });

        // need to run CD since file load runs outside of zone
        this.cd.markForCheck();
      };
    }
  }
  handleFileInput(files: FileList) {
    this.fileToUpload = files.item(0);
  }
  uploadFileCustomersToActivity() {
    this.fileService.postCustomersFile(this.fileToUpload).subscribe(data => {
      if (data === true) {
        alert('File ' + this.fileToUpload.name + ' uploaded successfully.');
      } else {
        alert('File not uploaded.');
      }
    }, error => {
      console.log(error);
    });
  }
  uploadFileProductsToActivity() {
    this.fileService.postProductsFile(this.fileToUpload).subscribe(data => {
      if (data === true) {
        alert('File ' + this.fileToUpload.name + ' uploaded successfully.');
      } else {
        alert('File not uploaded.');
      }
    }, error => {
      console.log(error);
    });
  }

}
