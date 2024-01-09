package com.dailycodelearner.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name="Book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="bookId")
    private Long id;
    private String name;
    @ManyToMany(cascade = {CascadeType.ALL},fetch = FetchType.LAZY)
    @JoinTable(name="book_author",
    joinColumns = {
            @JoinColumn(name="bookId",referencedColumnName = "bookId")
    },
    inverseJoinColumns = {
            @JoinColumn(name="authorId",referencedColumnName = "authorId")
    })
    private Set<Author> authors;
    @ManyToOne(cascade = {CascadeType.MERGE},fetch = FetchType.LAZY)
    @JoinColumn(name="categoryId",referencedColumnName = "categoryId")
    private Category category;

    public void addAuthor(Author author){
        this.authors.add(author);
        author.getBooks().add(this);
    }

    public void removeAuthor(Author author){
        this.authors.remove(author);
        author.getBooks().remove(this);
    }

    public void removeAuthors(){
        for(Author author:new HashSet<>(authors)){
            removeAuthor(author);
        }
    }
}
