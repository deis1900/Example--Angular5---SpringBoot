package com.AdminPanel.Angular5SpringBoot.repository;

import com.AdminPanel.Angular5SpringBoot.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    Invoice findByProductsId(Long id);
    Invoice findByCustomerId(Long id);
}
