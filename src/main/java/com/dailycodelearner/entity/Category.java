package com.dailycodelearner.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name="Category")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="categoryId")
    private Long id;
    private String name;
    @OneToMany(cascade = {CascadeType.MERGE},fetch=FetchType.LAZY,mappedBy="category")
    private Set<Book> books;

    public void addBook(Book save) {
        books.add(save);
    }

    public void removeBook(Book save) {
        books.remove(save);
    }
}
