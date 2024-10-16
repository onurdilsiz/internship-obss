package com.example.finalproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Seller extends BaseEntity{
    @Size(min = 3, max = 100)
    private String name;
    @Email
    private String email;
    @Size(min = 3, max = 100)
    @NotBlank
    private String phone;

    @NotBlank
    private String address;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product){
        products.add(product);
        product.setSeller(this);
    }

    public void removeProduct(Product product){
        products.remove(product);
        product.setSeller(null);
    }

    @JsonIgnore
    @ManyToMany(mappedBy = "blacklist")
    private List<User> usersBlacklisted =  new ArrayList<>();


}
