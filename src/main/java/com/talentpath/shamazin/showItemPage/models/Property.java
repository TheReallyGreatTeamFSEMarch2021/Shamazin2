package com.talentpath.shamazin.showItemPage.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
@Table(name="properties")
public class Property {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String value;

    @JsonIgnore
    @ManyToOne(fetch= FetchType.LAZY)
    @NotBlank
    private Item item;

    public Property() {

    }

    public Property(String name, String value, @NotBlank Item item) {
        this.name = name;
        this.value = value;
        this.item = item;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Property property = (Property) o;
        return name.equals(property.name) && value.equals(property.value) && Objects.equals(item, property.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value, item);
    }
}
