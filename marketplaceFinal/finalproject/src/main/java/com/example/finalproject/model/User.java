package com.example.finalproject.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class User  extends BaseEntity{

    @Column(unique = true)
    @Size(min=3, max=100)
    private String username;
    @NotBlank
    @Size(min=3, max=100)
    private String name;
    @NotBlank
    @Size(min=3, max=100)
    private String surname;
    @Email
    @NotBlank
    private String email;

    @Column
    private String password;

    @Column
    private Boolean enabled;


    @ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private List<Role> roles = new ArrayList<>();


    public void addRole(Role role){
        roles.add(role);
        role.getUsers().add(this);
    }

    public void removeRole(Role role){
        roles.remove(role);
        role.getUsers().remove(this);
    }


    @ManyToMany(fetch = FetchType.LAZY)
    private List<Product> favoriteProducts = new ArrayList<>();

    public void addFavoriteProduct(Product product){
        favoriteProducts.add(product);
        product.getUsersWhoFavorited().add(this);
    }
    public void removeFavoriteProduct(Product product){
        favoriteProducts.remove(product);
        product.getUsersWhoFavorited().remove(this);
    }

    @ManyToMany( fetch = FetchType.LAZY)
    private List<Seller> blacklist = new ArrayList<>();

    public void addSellerToBlacklist(Seller seller){
        blacklist.add(seller);
        seller.getUsersBlacklisted().add(this);
    }

    public void removeSellerFromBlacklist(Seller seller){
        blacklist.remove(seller);
        seller.getUsersBlacklisted().remove(this);
    }


    public Boolean getEnabled() {
        return true;
    }
}
