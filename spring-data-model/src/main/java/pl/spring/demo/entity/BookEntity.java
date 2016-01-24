package pl.spring.demo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "BOOK")
public class BookEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false, length = 50)
    private String title;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "book_author",
            joinColumns = {@JoinColumn(name = "BOOK_ID", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "AUTHOR_ID", nullable = false, updatable = false)})
    private Set<AuthorEntity> authors;
    @ManyToOne(optional = true)
    @JoinColumn(name = "LIBRARY_ID", nullable = true)
    private LibraryEntity library;

    // for hibernate
    protected BookEntity() {
    }

    public BookEntity(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<AuthorEntity> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<AuthorEntity> authors) {
        this.authors = authors;
    }

    public LibraryEntity getLibrary() {
        return library;
    }
}
