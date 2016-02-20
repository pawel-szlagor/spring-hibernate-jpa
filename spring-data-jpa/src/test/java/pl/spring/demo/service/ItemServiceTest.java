package pl.spring.demo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.spring.demo.to.ItemTo;

/**
 * Created by Paweł on 24.01.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonServiceTest-context.xml")
public class ItemServiceTest {

    @Autowired
    private ItemService itemService;

    @Test
    public void testShouldFindAllItems() {
        // given when
        List<ItemTo> items = itemService.findAllItems();
        // then
        assertNotNull(items);
        assertEquals(3, items.size());
    }

    @Test
    public void testShouldFindItemByInvoiceId() {
        // given
        final Long invoiceId = 1L;
        // when
        List<ItemTo> items = itemService.findByInvoiceId(invoiceId);
        // then
        assertNotNull(items);
        assertFalse(items.isEmpty());
        assertEquals(3, items.size());
    }

    @Test
    public void testShouldFindItemByProductId() {
        // given
        final Long productId = 1L;
        // when
        List<ItemTo> items = itemService.findByProductId(productId);
        // then
        assertNotNull(items);
        assertFalse(items.isEmpty());
        assertEquals(1, items.size());
    }
}