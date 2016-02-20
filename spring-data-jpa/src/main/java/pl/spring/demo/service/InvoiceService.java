package pl.spring.demo.service;

import java.util.List;

import pl.spring.demo.to.InvoiceTo;

/**
 * Created by Paweł on 24.01.2016.
 */
public interface InvoiceService {

    String amountInWords(double amount);
    List<InvoiceTo> findAllInvoices();
    InvoiceTo findInvoiceById(Long id);
}
