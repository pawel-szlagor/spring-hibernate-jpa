package pl.spring.demo.layout;

import pl.spring.demo.to.InvoiceTo;

/**
 * Created by Paweł on 27.01.2016.
 */
public interface InvoicePDFCreator {

    void createPdf(InvoiceTo invoice) throws Exception;
}
