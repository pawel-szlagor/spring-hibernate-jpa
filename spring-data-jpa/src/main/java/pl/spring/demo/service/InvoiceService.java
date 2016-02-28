package pl.spring.demo.service;

import java.math.BigDecimal;
import java.util.List;

import pl.spring.demo.to.InvoiceTo;

/**
 * Created by Paweł on 24.01.2016.
 */
public interface InvoiceService {

    String amountInWords(BigDecimal amount);
    List<InvoiceTo> findAllInvoices();
    InvoiceTo findInvoiceById(Long id);
}
