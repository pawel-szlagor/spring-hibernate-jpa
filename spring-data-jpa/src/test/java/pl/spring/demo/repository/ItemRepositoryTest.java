package pl.spring.demo.repository;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.spring.demo.entity.ItemEntity;
import pl.spring.demo.entity.ItemEntityId;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonRepositoryTest-context.xml")
public class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void testShouldFindItemByInvoiceId() {
        // given
        final Long invoiceId = 1L;
        // when
        List<ItemEntity> itemEntity = itemRepository.findByInvoiceId(invoiceId);
        // then
        assertNotNull(itemEntity);
        assertFalse(itemEntity.isEmpty());
    }

    @Test
    public void testShouldFindItemByProductId() {
        // given
        final Long productId = 1L;
        // when
        List<ItemEntity> itemEntity = itemRepository.findByProductId(productId);
        // then
        assertNotNull(itemEntity);
        assertFalse(itemEntity.isEmpty());
    }

    @Test
    public void testShouldFindItemById() {
        // given
        final ItemEntityId id = new ItemEntityId(1L, 1L);
        // when
        ItemEntity itemEntity = itemRepository.findOne(id);
        // then
        assertNotNull(itemEntity);
    }

}
