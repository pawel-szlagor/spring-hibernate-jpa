package pl.spring.demo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "BOOK")
public class BookEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false, length = 50)
    private String title;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "book_author",
            joinColumns = {@JoinColumn(name = "BOOK_ID", nullable = false, updatable = true)},
            inverseJoinColumns = {@JoinColumn(name = "AUTHOR_ID", nullable = false, updatable = true)})
    private List<AuthorEntity> authors;
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

    public List<AuthorEntity> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorEntity> authors) {
        this.authors = authors;
    }

    public LibraryEntity getLibrary() {
        return library;
    }
}
