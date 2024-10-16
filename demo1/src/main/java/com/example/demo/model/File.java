package com.example.demo.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class File extends BaseEntity{
    private String binaryContent;
}
