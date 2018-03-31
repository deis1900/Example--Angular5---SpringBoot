package com.AdminPanel.Angular5SpringBoot.controller;

import com.AdminPanel.Angular5SpringBoot.dto.InvoiceDto;
import com.AdminPanel.Angular5SpringBoot.model.Invoice;
import com.AdminPanel.Angular5SpringBoot.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

        List<Invoice> invoices = invoiceService.getAll();
        if(invoices.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        InvoiceDto wrapper  = new InvoiceDto();
        wrapper.setListInvoices(invoices);
        return new ResponseEntity<>(wrapper, HttpStatus.OK);
    }

    @GetMapping (value = "/{id}", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Invoice> getInvoiceId(@PathVariable Long id) {

        Invoice invoice = invoiceService.getInvoiceById(id);
        if(invoice == null) {
            System.out.println("Invoice with " + id + " not found.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(invoice, HttpStatus.OK);
    }

    @GetMapping(value="/customer/{id}",  produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Invoice> findByCustomerId(@PathVariable Long id) {

        Invoice invoice = invoiceService.findByCustomerId(id);
        if(invoice == null) {
            System.out.println("Unable to find.Invoice with Customer ID = " + id + " not found.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity<>(invoice, HttpStatus.OK);
    }

    @GetMapping(value="/product/{id}",  produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findByProductsId(@PathVariable Long id) {

        Invoice invoice = invoiceService.findByProductsId(id);
        if(invoice == null) {
            System.out.println("Unable to find.Invoice with Product ID = " + id + " not found.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(invoice, HttpStatus.OK);
    }

    @PostMapping (produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Invoice> postCustomer(@RequestBody Invoice invoice) {
        System.out.println("Creating invoice " + invoice.getId());

        if (invoice.equals(invoiceService.getInvoiceById(invoice.getId()))) {
            System.out.println("A Invoice with Id " + invoice.getId() + " already exist");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        invoiceService.save(new Invoice(
                invoice.getId(),
                invoice.getDateOfPurchase(),
                invoice.getAmount(),
                invoice.getCurrency(),
                invoice.getCustomer(),
                invoice.getProducts()
        ));
        return new ResponseEntity<>(invoice, HttpStatus.CREATED);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<Invoice> updateCustomer(@PathVariable ("id") Long id, @RequestBody Invoice invoice) {
        if (invoiceService.getInvoiceById(id) == null) {
            System.out.println("Invoice with Product ID = " + id + " not found.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (invoice.equals(invoiceService.getInvoiceById(invoice.getId())))
        invoiceService.update(invoice);
        return new ResponseEntity<>(invoice, HttpStatus.OK);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<?> deleteInvoice(@PathVariable Long id){
        if (invoiceService.getInvoiceById(id) == null) {
            System.out.println("Invoice with Product ID = " + id + " not found.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        invoiceService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
