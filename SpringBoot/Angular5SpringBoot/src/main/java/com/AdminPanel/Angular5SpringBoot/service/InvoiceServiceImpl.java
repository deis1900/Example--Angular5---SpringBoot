package com.AdminPanel.Angular5SpringBoot.service;

import com.AdminPanel.Angular5SpringBoot.model.Invoice;
import com.AdminPanel.Angular5SpringBoot.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    InvoiceRepository invoiceRepository;

    @Override
    @Transactional
    public Invoice findByProductsId(Long id) {
        return this.invoiceRepository.findByProductsId(id);
    }

    @Override
    @Transactional
    public Invoice findByCustomerId(Long id){
     return this.invoiceRepository.findByCustomerId(id);
    }

    @Override
    @Transactional
    public List<Invoice> getAll() {
        return this.invoiceRepository.findAll();
    }

    @Override
    @Transactional
    public Invoice getInvoiceById(Long id) {
        return this.invoiceRepository.getOne(id);
    }

    @Override
    @Transactional
    public void save(Invoice invoice) {
        this.invoiceRepository.save(invoice);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        this.invoiceRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void update(Invoice invoice) {
        this.invoiceRepository.saveAndFlush(invoice);
    }
}
