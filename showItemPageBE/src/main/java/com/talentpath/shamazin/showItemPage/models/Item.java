package com.talentpath.shamazin.showItemPage.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name="Items")
public class Item {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    @ManyToOne(fetch=FetchType.LAZY)
    @NotBlank
    private ItemFamily itemFamily;

    @OneToMany(mappedBy="item", cascade=CascadeType.ALL)
    private List<ProductPhoto> productPhotos;

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


}
