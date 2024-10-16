package com.example.finalproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Entity
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Role  extends BaseEntity{

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


    @Column(unique = true, nullable = false, length= 50)
    private String name;

    public Role(String name) {
        this.name = name;
    }

    @JsonIgnore
    @ManyToMany(mappedBy = "roles",fetch = FetchType.LAZY)
    private List<User> users = new ArrayList<>();


}
