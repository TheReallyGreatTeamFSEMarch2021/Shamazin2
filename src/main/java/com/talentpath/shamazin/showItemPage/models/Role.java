package com.talentpath.shamazin.showItemPage.models;

import javax.persistence.*;

@Entity
@Table(name="roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length=20)
    private RoleName role;

    public enum RoleName{
        ROLE_USER,
        ROLE_ADMIN,
        ROLE_SELLER
    }


    //getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RoleName getRole() {
        return role;
    }

    public void setRole(RoleName role) {
        this.role = role;
    }

    //equals and hashcode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role1 = (Role) o;

        if (!id.equals(role1.id)) return false;
        return role == role1.role;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + role.hashCode();
        return result;
    }
}
