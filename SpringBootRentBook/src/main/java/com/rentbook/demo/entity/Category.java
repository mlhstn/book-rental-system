package com.rentbook.demo.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "category-books")
    private List<Book> boks;

    public Category() {
    }

    public Category(Long id, String name, List<Book> boks) {
        this.id = id;
        this.name = name;
        this.boks = boks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBoks() {
        return boks;
    }

    public void setBoks(List<Book> boks) {
        this.boks = boks;
    }
}
