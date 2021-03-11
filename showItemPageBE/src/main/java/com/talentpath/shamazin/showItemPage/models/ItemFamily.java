package com.talentpath.shamazin.showItemPage.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name="ItemFamilies")
public class ItemFamily {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String familyName;

    @OneToMany(mappedBy="itemFamily", cascade=CascadeType.ALL)
    private List<Item> items;

    @OneToMany(mappedBy = "itemFamily", cascade=CascadeType.ALL)
    private List<Question> questions;

    @OneToMany(mappedBy="itemFamily", cascade=CascadeType.ALL)
    private List<Review> reviews;

    @NotBlank
    private String brand;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public ItemFamily(@NotBlank String familyName, List<Item> items, List<Question> questions, List<Review> reviews, @NotBlank String brand) {
        this.familyName = familyName;
        this.items = items;
        this.questions = questions;
        this.reviews = reviews;
        this.brand = brand;
    }

    public ItemFamily() {

    }
}
