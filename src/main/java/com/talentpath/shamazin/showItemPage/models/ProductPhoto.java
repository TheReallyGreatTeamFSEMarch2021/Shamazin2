package com.talentpath.shamazin.showItemPage.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cascade;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Entity
@Table(name="ProductPhotos")
public class ProductPhoto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String photoURL;

    @JsonIgnore
    @ManyToOne(fetch=FetchType.LAZY)
    @NotBlank
    private Item item;

    public ProductPhoto(){ };

    public ProductPhoto(String photoURL, Item relatedItem){
        this.photoURL = photoURL;
        this.item = relatedItem;
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}

