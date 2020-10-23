package com.rafsan.inventory.entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String productName;
    @Column(name = "priceBuy")
    private double priceBuy;
    @Column(name = "priceSell")
    private double priceSell;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "place")
    private String place;
    @Column(name = "description")
    private String description;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "categoryId")
    private Category category;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "supplierId")
    private Supplier supplier;

    public Product() {
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Product(long id, String productName, double priceBuy, double priceSell,
                   int quantity, String place, String description, Category category, Supplier supplier) {
        this.id = id;
        this.productName = productName;
        this.priceBuy = priceBuy;
        this.priceSell = priceSell;
        this.quantity = quantity;
        this.place = place;
        this.description = description;
        this.category = category;
        this.supplier = supplier;
    }

    public Product(String productName, double priceBuy, double priceSell,
                   int quantity, String place, String description, Category category, Supplier supplier) {
        this.productName = productName;
        this.priceBuy = priceBuy;
        this.priceSell = priceSell;
        this.quantity = quantity;
        this.description = description;
        this.place = place;
        this.category = category;
        this.supplier = supplier;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPriceBuy() {
        return priceBuy;
    }

    public void setPriceBuy(double priceBuy) {
        this.priceBuy = priceBuy;
    }

    public double getPriceSell() {
        return priceSell;
    }

    public void setPriceSell(double priceSell) {
        this.priceSell = priceSell;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id +
                ", productName=" + productName +
                ", priceBuy=" + priceBuy +
                ", priceSell=" + priceSell +
                ", quantity=" + quantity +
                ", place=" + place +
                ", description=" + description +
                ", category=" + category +
                ", supplier=" + supplier + '}';
    }
}
