package pl.spring.demo.to;

import java.util.Date;

/**
 * Created by Paweł on 18.01.2016.
 */
public class InvoiceTo {
    private Long id;
    private Date saleDate;
    private Date creationDate;
    private Date paymentDeadline;
    private String remarks;

    protected InvoiceTo() {
    }

    public InvoiceTo(Long id, Date saleDate, Date creationDate, Date paymentDeadline, String remarks) {
        this.id = id;
        this.saleDate = saleDate;
        this.creationDate = creationDate;
        this.paymentDeadline = paymentDeadline;
        this.remarks = remarks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
}
