package com.AdminPanel.Angular5SpringBoot.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Customer",
        indexes = {
        @Index(name = "customer_email", columnList = "email"),
        @Index(name = "customer_login", columnList = "userName"),
        @Index(name = "customer_phone", columnList = "phone") })
public class Customer implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String firstName;

    @Column
    String lastName;

    @Column(unique = true)
    String userName;

    @Column
    String password;

    @Column(unique = true)
    String email;

    @Column
    @Enumerated(EnumType.STRING)
    Sex gender;

    @Column(unique = true)
    Integer phone;

    @Column
    Boolean access;

    @Column
    String image;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Invoice> invoices = new ArrayList<>();

    public Customer(String firstName, String lastName, String userName, String password, String email,
                    Sex gender, Integer phone, Boolean access, String image) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.gender = gender;
        this.phone = phone;
        this.access = access;
        this.image = image;
    }

    protected Customer() {
    }

    public enum Sex {
        Men,
        Women
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Sex getGender() {
        return gender;
    }

    public void setGender(Sex gender) {
        this.gender = gender;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public Boolean getAccess() {
        return access;
    }

    public void setAccess(Boolean access) {
        this.access = access;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return String.format("Customer[id=%d, firstName='%s', lastName='%s', userName='%s'," +
                        "password='%s', email='%s', sex='%s', phone=%d, access='%s', image='%s']",
                id, firstName, lastName, userName, password, email, gender, phone, access, image);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (id != null ? !id.equals(customer.id) : customer.id != null) return false;
        if (userName != null ? !userName.equals(customer.userName) : customer.userName != null) return false;
        if (email != null ? !email.equals(customer.email) : customer.email != null) return false;
        return phone != null ? phone.equals(customer.phone) : customer.phone == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        return result;
    }
}