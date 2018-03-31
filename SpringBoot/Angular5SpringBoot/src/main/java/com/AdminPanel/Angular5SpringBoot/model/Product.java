package com.AdminPanel.Angular5SpringBoot.model;

import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String typeOfClothes;

    @Column
    private String material;

    @Column
    private String size;

    @Column
    private String color;

    @Column
    private Long dateOfLastChange;

    @Column
    private String image;

    @ManyToMany(targetEntity = Invoice.class)
    private List<Invoice> invoices = new ArrayList<>();

    public Product(Long id, String typeOfClothes, String material, String size, String color, Long dateOfLastChange, String image) {
        this.id = id;
        this.typeOfClothes = typeOfClothes;
        this.material = material;
        this.size = size;
        this.color = color;
        this.dateOfLastChange = dateOfLastChange;
        this.image = image;
    }

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeOfClothes() {
        return typeOfClothes;
    }

    public void setTypeOfClothes(String typeOfClothes) {
        this.typeOfClothes = typeOfClothes;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Long getDateOfLastChange() {
        return dateOfLastChange;
    }

    public void setDateOfLastChange(Long dateOfLastChange) {
        this.dateOfLastChange = dateOfLastChange;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return String.format("Product[id=%d, typeOfClothes='%s', material='%s', size='%s'," +
                        "color='%s', dateOfLastChange=%d, image='%s']",
                id, typeOfClothes, material, size, color, dateOfLastChange, image);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (id != null ? !id.equals(product.id) : product.id != null) return false;
        if (typeOfClothes != null ? !typeOfClothes.equals(product.typeOfClothes) : product.typeOfClothes != null)
            return false;
        if (material != null ? !material.equals(product.material) : product.material != null) return false;
        if (size != null ? !size.equals(product.size) : product.size != null) return false;
        if (color != null ? !color.equals(product.color) : product.color != null) return false;
        if (dateOfLastChange != null ? !dateOfLastChange.equals(product.dateOfLastChange) : product.dateOfLastChange != null)
            return false;
        return image != null ? image.equals(product.image) : product.image == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (typeOfClothes != null ? typeOfClothes.hashCode() : 0);
        result = 31 * result + (material != null ? material.hashCode() : 0);
        result = 31 * result + (size != null ? size.hashCode() : 0);
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + (dateOfLastChange != null ? dateOfLastChange.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        return result;
    }
}

