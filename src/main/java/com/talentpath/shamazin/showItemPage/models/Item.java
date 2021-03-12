package com.talentpath.shamazin.showItemPage.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="Items")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Item {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @NotBlank
    private ItemFamily itemFamily;

    @OneToMany(mappedBy="item", cascade=CascadeType.ALL)
    private List<ProductPhoto> productPhotos;

    @OneToMany(mappedBy="item", cascade=CascadeType.ALL)
    private List<Property> properties;

    @NotBlank
    private String name;

    @NotBlank
    private Double price;

    @NotBlank
    private Integer stockRemaining;

    @NotBlank
    private Boolean isPrimeEligible;

    public List<ProductPhoto> getProductPhotos() {
        return productPhotos;
    }

    public void setProductPhotos(List<ProductPhoto> productPhotos) {
        this.productPhotos = productPhotos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ItemFamily getItemFamily() {
        return itemFamily;
    }

    public void setItemFamily(ItemFamily itemFamily) {
        this.itemFamily = itemFamily;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStockRemaining() {
        return stockRemaining;
    }

    public void setStockRemaining(Integer stockRemaining) {
        this.stockRemaining = stockRemaining;
    }

    public Boolean getPrimeEligible() {
        return isPrimeEligible;
    }

    public void setPrimeEligible(Boolean primeEligible) {
        isPrimeEligible = primeEligible;
    }

    public Item() {

    }

    public Item(@NotBlank ItemFamily itemFamily, List<ProductPhoto> productPhotos, @NotBlank String name, @NotBlank Double price, @NotBlank Integer stockRemaining, @NotBlank Boolean isPrimeEligible) {
        this.itemFamily = itemFamily;
        this.productPhotos = productPhotos;
        this.name = name;
        this.price = price;
        this.stockRemaining = stockRemaining;
        this.isPrimeEligible = isPrimeEligible;
    }

    public Item(@NotBlank ItemFamily itemFamily, List<ProductPhoto> productPhotos, List<Property> properties, @NotBlank String name, @NotBlank Double price, @NotBlank Integer stockRemaining, @NotBlank Boolean isPrimeEligible) {
        this.itemFamily = itemFamily;
        this.productPhotos = productPhotos;
        this.properties = properties;
        this.name = name;
        this.price = price;
        this.stockRemaining = stockRemaining;
        this.isPrimeEligible = isPrimeEligible;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return itemFamily.equals(item.itemFamily) && Objects.equals(properties,item.properties) && Objects.equals(productPhotos, item.productPhotos) && name.equals(item.name) && price.equals(item.price) && stockRemaining.equals(item.stockRemaining) && isPrimeEligible.equals(item.isPrimeEligible);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemFamily, productPhotos, name, price, stockRemaining, isPrimeEligible, properties);
    }
}
