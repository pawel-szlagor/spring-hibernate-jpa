package pl.spring.demo.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.spring.demo.entity.AuthorEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonRepositoryTest-context.xml")
public class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void testShouldFindAuthorById() {
        // given
        final long id = 1;
        // when
        AuthorEntity authorEntity = authorRepository.findOne(id);
        // then
        assertNotNull(authorEntity);
        assertEquals(authorEntity.getPublicationsNumber(), 15);
        assertEquals(authorEntity.getAge(), 44);
        assertEquals(authorEntity.getFirstName(), "Jan");
        assertEquals(authorEntity.getId().longValue(), 1L);
        assertEquals(authorEntity.getLastName(), "Kowalski");
        assertEquals(authorEntity.getBooks().size(), 1);
    }


}
