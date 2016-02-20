package pl.spring.demo.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.spring.demo.entity.InvoiceEntity;

/**
 * Created by Paweł on 28.01.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonRepositoryTest-context.xml")
public class InvoiceRepositoryTest {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Test
    public void testShouldFindInvoiceById() {
        // given
        final long invoiceId = 1;
        // when
        InvoiceEntity invoiceEntity = invoiceRepository.findOne(invoiceId);
        // then
        assertNotNull(invoiceEntity);
        assertEquals(3, invoiceEntity.getItems().size());
        assertNotNull(invoiceEntity.getCustomer());
    }

}