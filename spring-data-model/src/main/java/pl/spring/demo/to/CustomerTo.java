package pl.spring.demo.to;

import java.util.Set;

public class CustomerTo extends CompanyTo{

    private Set<InvoiceTo> invoices;

    public Set<InvoiceTo> getInvoices() {
        return invoices;
    }

    public void setInvoices(Set<InvoiceTo> invoices) {
        this.invoices = invoices;
    }

    // for hibernate
    protected CustomerTo() {
    }


    public CustomerTo(Set<InvoiceTo> invoices) {
        this.invoices = invoices;
    }

}
