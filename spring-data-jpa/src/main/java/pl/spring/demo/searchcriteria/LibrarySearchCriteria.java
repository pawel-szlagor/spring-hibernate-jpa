package pl.spring.demo.searchcriteria;

import java.io.Serializable;

public class LibrarySearchCriteria implements Serializable {

    private Long id;
    private String name;
    private String bookTitle;

    private LibrarySearchCriteria() {

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public static class LibrarySearchCriteriaBuilder {
        private LibrarySearchCriteria librarySearchCriteria = new LibrarySearchCriteria();

        public LibrarySearchCriteriaBuilder witId(long id) {
            librarySearchCriteria.id = id;
            return this;
        }

        public LibrarySearchCriteriaBuilder withName(String name) {
            librarySearchCriteria.name = name;
            return this;
        }

        public LibrarySearchCriteriaBuilder withBook(String bookTitle) {
            librarySearchCriteria.bookTitle = bookTitle;
            return this;
        }

        public LibrarySearchCriteria build() {
            return librarySearchCriteria;
        }
    }
}
