package pl.spring.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "AUTHOR")
@PrimaryKeyJoinColumn(name = "author_id", referencedColumnName = "id")
public class AuthorEntity extends PersonEntity implements Serializable {
    @Column(name = "publications")
    private int publicationsNumber;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "book_author",
            joinColumns = {@JoinColumn(name = "AUTHOR_ID", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "BOOK_ID", nullable = false, updatable = true)})
    private Set<BookEntity> books;

    public int getPublicationsNumber() {
        return publicationsNumber;
    }

    public void setPublicationsNumber(int publicationsNumber) {
        this.publicationsNumber = publicationsNumber;
    }

    public Set<BookEntity> getBooks() {
        return books;
    }

    public void setBooks(Set<BookEntity> books) {
        this.books = books;
    }
}
