import {Invoice} from './invoice';
import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';


Injectable();

export class InvoiceService {
  private myHeaders = new HttpHeaders().set('guest', 'guest');
  private invoices: Invoice[] = [
    {
      id: 1, customer:
        {
          id: 1, firstName: 'den', lastName: 'Denisov', userName: 'DDen', email: 'Den@den.com', sex: 'Men',
          password: '888888888',
          access: true,
          image: 'https://image.flaticon.com/icons/svg/667/667189.svg',
          phone: 888888888
        },
      amount: 560, products:[
        {
          id: 6,
          typeOfClothes: 'Pants',
          material: 'Jeans',
          color: 'Blue',
          size: 'S, M, L, XL',
          dateOfLastChange: 6546512168589,
          image: 'https://images.ua.prom.st/705093676_w800_h640_cid2327845_pid495533956-21765ebf.jpg'
        },
        {
          id: 7,
          typeOfClothes: 'Skirt',
          material: 'leather',
          color: 'Black',
          size: 'S, M, L, XL',
          dateOfLastChange: 6546512168589,
          image: 'https://images.ua.prom.st/340163413_w800_h640_zspighkdits1.jpg'
        }
      ], checkDate: 908097870979
    },
    {
      id: 2, customer:
        {
          id: 4, firstName: 'Dim', lastName: 'Dimov', userName: 'Dim', email: 'Dim@den.com', sex: 'Men',
          password: '22322322',
          access: true,
          image: 'https://www.cambio16.com/wp-content/uploads/2017/08/foto-el-nino-del-meme-triunfal-reaparece-10-anos-despues.jpg',
          phone: 2309230923
        },
      amount: 880, products: [
        {
          id: 6,
          typeOfClothes: 'Pants',
          material: 'Jeans',
          color: 'Blue',
          size: 'S, M, L, XL',
          dateOfLastChange: 6546512168589,
          image: 'https://images.ua.prom.st/705093676_w800_h640_cid2327845_pid495533956-21765ebf.jpg'
        },
        {
          id: 3,
          typeOfClothes: 'Dress',
          material: 'Cotton',
          color: 'Black',
          size: '52, 54, 56, 58',
          dateOfLastChange: 78454841589,
          image: 'https://images.ua.prom.st/705093901_w800_h640_cid2327845_pid495533958-ff414cee.jpg'
        },
        {
          id: 8,
          typeOfClothes: 'Suit',
          material: 'Velvet',
          color: 'Red',
          size: 'S, M, L, XL',
          dateOfLastChange: 6546512168589,
          image: 'https://avenue-boutique.com.ua/media/catalog/product/cache/4/image/560x800/' +
          'ea8fadf1dc16eb15153bb4d92cdfabcf/_/c/_carica_km-2063_1.jpg'
        },
      ], checkDate: 778898999877 },
    {id: 3,  customer:
        {id: 2, firstName: 'Lara', lastName: 'Larisova', userName: 'Lar', email: 'LarL@den.com', sex: 'Women',
          password: '8888880009',
          access: true,
          image: 'https://image.flaticon.com/icons/svg/672/672642.svg',
          phone: 888888809},
      amount: 1050, products: [
        {
          id: 3,
          typeOfClothes: 'Dress',
          material: 'Cotton',
          color: 'Black',
          size: '52, 54, 56, 58',
          dateOfLastChange: 78454841589,
          image: 'https://images.ua.prom.st/705093901_w800_h640_cid2327845_pid495533958-ff414cee.jpg'
        },
        {
          id: 4,
          typeOfClothes: 'Dress',
          material: 'Jeans',
          color: 'White-Blue',
          size: '42, 44, 46, 48',
          dateOfLastChange: 2151681152589,
          image: 'https://images.ua.prom.st/705095226_w800_h640_cid2327845_pid495533994-eb679f9b.jpg'
        }
      ], checkDate: 989887868988},
    {id: 4, customer:
        {id: 2, firstName: 'Lara', lastName: 'Larisova', userName: 'Lar', email: 'LarL@den.com', sex: 'Women',
          password: '8888880009',
          access: true,
          image: 'https://image.flaticon.com/icons/svg/672/672642.svg',
          phone: 888888809},
      amount: 2500, products: [
        {
          id: 2,
          typeOfClothes: 'Dress',
          material: 'Viscose',
          color: 'Blue',
          size: 'S, M, L',
          dateOfLastChange: 6878954954565,
          image: 'https://images.ua.prom.st/705057135_w800_h640_cid2327845_pid495526667-ee8b7bfe.jpg'
        },
        {
          id: 3,
          typeOfClothes: 'Dress',
          material: 'Cotton',
          color: 'Black',
          size: '52, 54, 56, 58',
          dateOfLastChange: 78454841589,
          image: 'https://images.ua.prom.st/705093901_w800_h640_cid2327845_pid495533958-ff414cee.jpg'
        },
        {
          id: 4,
          typeOfClothes: 'Dress',
          material: 'Jeans',
          color: 'White-Blue',
          size: '42, 44, 46, 48',
          dateOfLastChange: 2151681152589,
          image: 'https://images.ua.prom.st/705095226_w800_h640_cid2327845_pid495533994-eb679f9b.jpg'
        }
      ], checkDate: 7887867986879}
  ];
  // constructor(private http: HttpClient) { }
  getInvoice() {
    return this.invoices;
  }

  // getInvoices(): Observable<Invoice[]> {
  //  return this.http.get('http://localhost:8080/invoice/invoices')
  //    .map(data => {
  //      let invoiceList = data['invoicesList'];
  //      return invoiceList.map(function (invoice: any) {
  //        return {
  //          id: invoice.id,
  //          lastName: invoice.amount,
  //          checkDate: invoice.checkDate,
  //          customer: invoice.customer,
  //          products: invoice.products,
  //        };
  //       });
  //     })
  //    .catch((error: any) => {
  //      console.log(error);
  //      return Observable.throw(error);
  //    });
  // }
  // postInvoice(invoice: Invoice) {
  //   const body = {
  //     id: invoice.id,
  //     amount: invoice.amount,
  //     checkDate: invoice.checkDate,
  //     customer: invoice.customer,
  //     products: invoice.products,
  //   };
  //   return this.http.post('http://localhost:8080/invoice/add?body=', body, {headers: this.myHeaders});
  // }
  //
  // updateInvoice(invoice: Invoice) {
  //   const body = {
  //     id: invoice.id,
  //     amount: invoice.amount,
  //     checkDate: invoice.checkDate,
  //     customer: invoice.customer,
  //     products: invoice.products,
  //   };
  //   return this.http.put(`http://localhost:8080/invoice/edit/${invoice.id}`, body);
  // }
  // deleteInvoice(id) {
  //   return this.http.delete(`http://localhost:8080/invoice/remove/${id}`, {headers: this.myHeaders});
  // }
  postInvoice(inivoice: Invoice) {
    this.invoices.push(inivoice);
  }
}
