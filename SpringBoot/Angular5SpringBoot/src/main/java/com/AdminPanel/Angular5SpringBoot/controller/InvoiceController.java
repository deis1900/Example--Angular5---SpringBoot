package com.AdminPanel.Angular5SpringBoot.controller;

import com.AdminPanel.Angular5SpringBoot.dto.InvoiceDto;
import com.AdminPanel.Angular5SpringBoot.model.Invoice;
import com.AdminPanel.Angular5SpringBoot.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/invoice")
public class InvoiceController {

    private InvoiceService invoiceService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService){
        this.invoiceService = invoiceService;
    }

    @GetMapping (produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InvoiceDto> getAll() {
        InvoiceDto wrapper  = new InvoiceDto();
        wrapper.setListInvoices(invoiceService.getAll());

        return new ResponseEntity<>(wrapper, HttpStatus.OK);
    }
    @GetMapping (value = "/{id}", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Invoice> getInvoiceId(@PathVariable Long id) {
        Invoice invoice = invoiceService.getInvoiceById(id);
        return new ResponseEntity<>(invoice, HttpStatus.OK);
    }

    @PostMapping (produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> postCustomer(@RequestBody Invoice invoice) {
        System.out.println(invoice.toString());
        invoiceService.save(new Invoice(
                invoice.getId(),
                invoice.getDateOfPurchase(),
                invoice.getAmount(),
                invoice.getCurrency(),
                invoice.getCustomer(),
                invoice.getProducts()
        ));
        return new ResponseEntity<>(invoice, HttpStatus.OK);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<Invoice> updateCustomer(@RequestBody Invoice invoice) {
        invoiceService.update(invoice);
        return new ResponseEntity<>(invoice, HttpStatus.OK);
    }

    @GetMapping(value="/user/{id}",  produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findByCustomerId(@PathVariable Long id) {
        Invoice invoice = invoiceService.findByCustomerId(id);
        return  new ResponseEntity<>(invoice, HttpStatus.OK);
    }

    @GetMapping(value="/products/{id}",  produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findByProductsId(@PathVariable Long id) {
        Invoice invoice = invoiceService.findByProductsId(id);
        return new ResponseEntity<>(invoice, HttpStatus.OK);
    }

    @DeleteMapping(value="/{id}")
    public void deleteInvoice(@PathVariable Long id){
        invoiceService.delete(id);
    }
}
