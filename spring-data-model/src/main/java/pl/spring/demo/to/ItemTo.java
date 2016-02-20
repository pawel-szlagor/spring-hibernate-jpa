package pl.spring.demo.to;

import org.joda.money.Money;

/**
 * Created by Paweł on 29.01.2016.
 */
public class ItemTo {

    private Double quantity;
    private Long invoiceId;
    private Long productId;
    private InvoiceTo invoice;
    private ProductTo product;
    private Money totalNetto;
    private Money taxValue;
    private Money totalGross;

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public InvoiceTo getInvoice() {
        return invoice;
    }

    public void setInvoice(InvoiceTo invoice) {
        this.invoice = invoice;
    }

    public ProductTo getProduct() {
        return product;
    }

    public void setProduct(ProductTo product) {
        this.product = product;
    }

    public Money getTotalNetto() {
        return totalNetto;
    }

    public void setTotalNetto(Money totalNetto) {
        this.totalNetto = totalNetto;
    }

    public Money getTaxValue() {
        return taxValue;
    }

    public void setTaxValue(Money taxValue) {
        this.taxValue = taxValue;
    }

    public Money getTotalGross() {
        return totalGross;
    }

    public void setTotalGross(Money totalGross) {
        this.totalGross = totalGross;
    }
}
