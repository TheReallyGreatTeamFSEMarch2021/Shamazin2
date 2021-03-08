package com.talentpath.shamazin.showItemPage.models;

import javax.persistence.*;

@Entity
@Table(name="Info")
public class Info {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String value;

    public Info() {
    }

    public Info(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Info info = (Info) o;

        if (!id.equals(info.id)) return false;
        return value.equals(info.value);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + value.hashCode();
        return result;
    }
}
