package com.example.finalproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Product extends BaseEntity {

    @Size(min = 3, max = 100)
    private String name;
    @Size(min = 3, max = 100)
    private String description;

    @NotBlank
    private String category;

    @NotNull
    private Double price;
    @NotNull
    private Integer quantity;

    @NotNull
    @ManyToOne
    private Seller seller;

    @ManyToMany(mappedBy = "favoriteProducts")
    @JsonIgnore
    private List<User> usersWhoFavorited = new ArrayList<>();


}

