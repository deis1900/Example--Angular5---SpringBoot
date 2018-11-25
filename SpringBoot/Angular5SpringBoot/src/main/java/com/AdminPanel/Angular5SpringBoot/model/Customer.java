package com.AdminPanel.Angular5SpringBoot.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Customer",
        indexes = {
                @Index(name = "customer_email", columnList = "email"),
                @Index(name = "customer_login", columnList = "userName"),
                @Index(name = "customer_phone", columnList = "phone")})
public class Customer implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotEmpty(message = "*Please provide your first role")
    private String firstName;

    @Column
    @NotEmpty(message = "*Please provide your last role")
    private String lastName;

    @Column(unique = true)
    @NotEmpty(message = "*Please provide your login")
    private String userName;

    @Column(unique = true)
    @Email(message = "Please provide a valid e-mail")
    @NotEmpty(message = "Please provide an e-mail")
    private String email;

    @Column
    @Enumerated(EnumType.STRING)
    private Sex gender;

    @Column(unique = true)
    @NotNull(message = "*Please provide your phone")
    private Long phone;

    @Column
    private Boolean access;

    @Column
    private String image;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Invoice> invoices;

    public Customer(String firstName, String lastName, String userName, String email,
                    Sex gender, Long phone, Boolean access, String image) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.gender = gender;
        this.phone = phone;
        this.access = access;
        this.image = image;
    }

    public Customer() {
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

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
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
                        " email='%s', gender='%s', phone=%d, access='%s', image='%s']",
                id, firstName, lastName, userName, email, gender, phone, access, image);
    }


}