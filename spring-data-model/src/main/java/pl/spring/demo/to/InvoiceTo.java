package pl.spring.demo.to;

import java.util.Date;
import java.util.List;

import org.joda.money.Money;

/**
 * Created by Paweł on 18.01.2016.
 */
public class InvoiceTo {
    protected Long id;
    protected Date saleDate;
    protected Date creationDate;
    protected Date paymentDeadline;
    protected String remarks;
    protected CustomerTo customer;
    protected List<ItemTo> items;
    protected Money totalNetto;

    public InvoiceTo() {
    }

    public InvoiceTo(Long id, Date saleDate, Date creationDate, Date paymentDeadline, String remarks, CustomerTo customer, List<ItemTo> items, Money totalNetto) {
        this.id = id;
        this.saleDate = saleDate;
        this.creationDate = creationDate;
        this.paymentDeadline = paymentDeadline;
        this.remarks = remarks;
        this.customer = customer;
        this.items = items;
        this.totalNetto = totalNetto;
    }

    public Long getid() {
        return id;
    }

    public void setid(Long id) {
        this.id = id;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getPaymentDeadline() {
        return paymentDeadline;
    }

    public void setPaymentDeadline(Date paymentDeadline) {
        this.paymentDeadline = paymentDeadline;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public CustomerTo getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerTo customer) {
        this.customer = customer;
    }

    public List<ItemTo> getItems() {
        return items;
    }

    public void setItems(List<ItemTo> items) {
        this.items = items;
    }

    public Money getTotalNetto() {
        return totalNetto;
    }

    public void setTotalNetto(Money totalNetto) {
        this.totalNetto = totalNetto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InvoiceTo)) return false;

        InvoiceTo invoiceTo = (InvoiceTo) o;

        return id.equals(invoiceTo.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        //sb.append("Invoice id: ").append(id).append(" Date: ").append(creationDate).append(" Total cost: ").append(totalNetto).append("\u20ac\n");
        sb.append("Customer: ").append(customer.toString()).append("\n");
        for (ItemTo item : items) {
            sb.append(item.toString()).append("\n");
        }
        return sb.toString();
    }
}
