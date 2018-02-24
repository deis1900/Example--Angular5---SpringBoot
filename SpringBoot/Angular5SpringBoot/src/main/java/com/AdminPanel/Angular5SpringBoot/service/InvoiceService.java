package com.AdminPanel.Angular5SpringBoot.service;

import com.AdminPanel.Angular5SpringBoot.model.Invoice;

import java.util.List;

public interface InvoiceService {

    Invoice findByProductsId(Long id);

    Invoice findByCustomerId(Long id);

    List<Invoice> getAll();

    Invoice getInvoiceById(Long id);

    void save(Invoice invoice);

    void delete(Long id);

    void update(Invoice invoice);
}
