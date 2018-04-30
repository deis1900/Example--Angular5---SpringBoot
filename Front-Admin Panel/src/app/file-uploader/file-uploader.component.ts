import {Component} from '@angular/core';
import {FileUploaderService} from './file-uploader.service';

@Component({
  selector: 'app-file-uploader',
  templateUrl: './file-uploader.component.html',
  styleUrls: ['./file-uploader.component.scss']
})
export class FileUploaderComponent  {

  fileToUpload: File = null;
  constructor(private fileService: FileUploaderService) {}

  handleFileInput(files: FileList) {
    this.fileToUpload = files.item(0);
  }
  // Обьединить методы и добавить параметр с выбором и отправкой в Service.

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
