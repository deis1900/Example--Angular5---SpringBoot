package com.AdminPanel.Angular5SpringBoot.dto;

import com.AdminPanel.Angular5SpringBoot.model.Invoice;

import java.io.Serializable;
import java.util.List;

public class InvoiceDto implements Serializable{

    private List<Invoice> invoiceList;

    public List<Invoice> getListInvoice() {
        return invoiceList;
    }

    public void setListInvoices(List<Invoice> customers) {
        this.invoiceList = customers;
    }
}
