package pl.spring.demo.layout;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.spring.demo.service.InvoiceService;
import pl.spring.demo.to.InvoiceTo;

/**
 * Created by Paweł on 27.01.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration(locations = "CommonLayoutTest-context.xml")

public class InvoicePDFCreatorImplTest {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private InvoicePDFCreator invoicePDFCreator;

    private InvoiceTo invoice;

    @Before
    public void setUp() throws Exception {
        invoice = invoiceService.findInvoiceById(1L);
    }

    @Test
    public void shouldPrintInvoiceInPDF() throws Exception {
        invoicePDFCreator.createPdf(invoice);
    }

}