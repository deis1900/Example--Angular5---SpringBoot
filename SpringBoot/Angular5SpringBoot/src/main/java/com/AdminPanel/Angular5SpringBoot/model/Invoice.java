package com.AdminPanel.Angular5SpringBoot.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class Invoice implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long dateOfPurchase;

    @Column
    private Double amount;

    @Column
    private String currency;

    @ManyToOne(optional=false)
    @JoinColumn(name="customer_id", nullable=false, updatable=false)
    private Customer customer;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Invoices_Products",
            joinColumns = { @JoinColumn(name = "invoice_id", nullable = false) },
            inverseJoinColumns = { @JoinColumn(name = "product_id", nullable = false) }
    )
    private List<Product> products = new ArrayList<>();

    public Invoice() {
    }

    public Invoice(Long id, Long dateOfPurchase, Double amount, String  currency,
                   Customer customer, List<Product> products) {
        this.id = id;
        this.dateOfPurchase = dateOfPurchase;
        this.amount = amount;
        this.currency = currency;
        this.customer = customer;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(Long dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Customer getCustomer() { return customer; }

    @Override
    public String toString() {
        return String.format("Invoice[id=%d, dateOfPurchase='%d', " +
                        "amount='%s', currency='%s', products='[ ]', customer='[]']",
                id, dateOfPurchase, amount, currency, products.toString(), customer.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Invoice invoice = (Invoice) o;

        if (id != null ? !id.equals(invoice.id) : invoice.id != null) return false;
        if (dateOfPurchase != null ? !dateOfPurchase.equals(invoice.dateOfPurchase) : invoice.dateOfPurchase != null)
            return false;
        if (amount != null ? !amount.equals(invoice.amount) : invoice.amount != null) return false;
        if (currency != null ? !currency.equals(invoice.currency) : invoice.currency != null) return false;
        if (products != null ? !products.equals(invoice.products) : invoice.products != null) return false;
        return customer != null ? customer.equals(invoice.customer) : invoice.customer == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (dateOfPurchase != null ? dateOfPurchase.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        result = 31 * result + (products != null ? products.hashCode() : 0);
        result = 31 * result + (customer != null ? customer.hashCode() : 0);
        return result;
    }

//    public enum OrderStatus {
//        RECEIVED,
//        PROCESSED,
//        DELIVERED,
//        RETURNED;
//    }
}
