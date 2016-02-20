package pl.spring.demo.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.spring.demo.entity.BookEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonRepositoryTest-context.xml")
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void testShouldFindBookById() {
        // given
        final long bookId = 1;
        // when
        BookEntity bookEntity = bookRepository.findOne(bookId);
        // then
        assertNotNull(bookEntity);
    }

    @Test
    public void testShouldFindBookWithAuthor() {
        // given
        final long bookId = 2;
        // when
        BookEntity bookEntity = bookRepository.findOne(bookId);
        // then
        assertEquals(2, bookEntity.getAuthors().size());
    }

    @Test
    public void testShouldFinAllBooks() {
        // given
        // when
        List<BookEntity> books = bookRepository.findAll();
        // then
        assertEquals(3, books.size());
    }
}
