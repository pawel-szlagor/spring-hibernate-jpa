package pl.spring.demo.to;

import java.util.Set;

import pl.spring.demo.entity.InvoiceEntity;

public class CustomerTo {

    private Set<InvoiceEntity> invoices;

    public Set<InvoiceEntity> getInvoices() {
        return invoices;
    }

    public void setInvoices(Set<InvoiceEntity> invoices) {
        this.invoices = invoices;
    }

    // for hibernate
    protected CustomerTo() {
    }


    public CustomerTo(Set<InvoiceEntity> invoices) {
        this.invoices = invoices;
    }

}
