package com.talentpath.shamazin.showItemPage.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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

    @JsonIgnore
    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
            name="RelatedBoughtItems",
            joinColumns={@JoinColumn(name="itemId")},
            inverseJoinColumns={@JoinColumn(name="relatedBoughtItemId")})
    private Set<Item> relatedBoughtItems;

    @JsonIgnore
    @ManyToMany(mappedBy="relatedBoughtItems")
    private Set<Item> items;

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

    public Set<Item> getRelatedBoughtItems() { return relatedBoughtItems; }

    public void setRelatedBoughtItems(Set<Item> relatedBoughtItems) { this.relatedBoughtItems = relatedBoughtItems; }

    public Set<Item> getItems() { return items; }

    public void setItems(Set<Item> items) { this.items = items; }

    public Item() { }

    public Item(@NotBlank ItemFamily itemFamily, List<ProductPhoto> productPhotos, @NotBlank String name, @NotBlank Double price, @NotBlank Integer stockRemaining, @NotBlank Boolean isPrimeEligible) {
        this.itemFamily = itemFamily;
        this.productPhotos = productPhotos;
        this.name = name;
        this.price = price;
        this.stockRemaining = stockRemaining;
        this.isPrimeEligible = isPrimeEligible;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return itemFamily.equals(item.itemFamily) && Objects.equals(productPhotos, item.productPhotos) && name.equals(item.name) && price.equals(item.price) && stockRemaining.equals(item.stockRemaining) && isPrimeEligible.equals(item.isPrimeEligible);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemFamily, productPhotos, name, price, stockRemaining, isPrimeEligible);
    }
}
