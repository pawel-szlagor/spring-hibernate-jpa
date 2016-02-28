package pl.spring.demo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.List;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.spring.demo.to.InvoiceTo;

/**
 * Created by Paweł on 24.01.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonServiceTest-context.xml")
public class InvoiceServiceTest {

    @Autowired
    private InvoiceService invoiceService;

    @Test
    public void testShouldCalculateTotamSumNetto() {
        // given when
        InvoiceTo invoice = invoiceService.findInvoiceById(1L);
        // then
        assertEquals(Money.of(CurrencyUnit.of("PLN"), 2611.80), invoice.getTotalNetto());
    }

    @Test
    public void testShouldFindAllInvoices() {
        // given when
        List<InvoiceTo> invoices = invoiceService.findAllInvoices();
        // then
        assertNotNull(invoices);
        assertEquals(1, invoices.size());
    }

    @Test
    public void shouldChangePositiveNonDecimalAmountToWords() {
        //given
        BigDecimal amount = BigDecimal.valueOf(121);
        //when
        String inWords = invoiceService.amountInWords(amount);
        //then
        assertEquals("sto dwadzieścia jeden złotych, 00/100", inWords);
    }

    @Test
    public void shouldChangeBigPositiveNonDecimalAmountToWords() {
        //given
        BigDecimal amount = BigDecimal.valueOf(1234567);
        //when
        String inWords = invoiceService.amountInWords(amount);
        //then
        assertEquals("milion dwieście trzydzieści cztery tysiące pięćset sześćdziesiąt siedem złotych, 00/100", inWords);
    }

    @Test
    public void shouldChangePositiveDecimalAmountToWords() {
        //given
        BigDecimal amount = BigDecimal.valueOf(124.45);
        //when
        String inWords = invoiceService.amountInWords(amount);
        //then
        assertEquals("sto dwadzieścia cztery złote, 45/100", inWords);
    }

    @Test
    public void shouldChangePositiveNonRegularDecimalAmountToWords() {
        //given
        BigDecimal amount = BigDecimal.valueOf(122.45);
        //when
        String inWords = invoiceService.amountInWords(amount);
        //then
        assertEquals("sto dwadzieścia dwa złote, 45/100", inWords);
    }

    @Test
    public void shouldChangeBigPositiveDecimalAmountToWords() {
        //given
        BigDecimal amount = BigDecimal.valueOf(1234567.89);
        //when
        String inWords = invoiceService.amountInWords(amount);
        //then
        assertEquals("milion dwieście trzydzieści cztery tysiące pięćset sześćdziesiąt siedem złotych, 89/100", inWords);
    }

    @Test
    public void shouldChangeBigPositiveDecimalWithTenAmountToWords() {
        //given
        BigDecimal amount = BigDecimal.valueOf(1200560.10);
        //when
        String inWords = invoiceService.amountInWords(amount);
        //then
        assertEquals("milion dwieście tysięcy pięćset sześćdziesiąt złotych, 10/100", inWords);
    }

    @Test
    public void shouldChangeZeroToWords() {
        //given
        BigDecimal amount = BigDecimal.ZERO;
        //when
        String inWords = invoiceService.amountInWords(amount);
        //then
        assertEquals("zero złotych, 00/100", inWords);
    }

    @Test
    public void shouldChangeZeroInDecimalToWords() {
        //given
        BigDecimal amount = BigDecimal.valueOf(0.00);
        //when
        String inWords = invoiceService.amountInWords(amount);
        //then
        assertEquals("zero złotych, 00/100", inWords);
    }
}