package pl.spring.demo.entity;

import java.io.Serializable;

/**
 * Created by Paweł on 30.01.2016.
 */
public class ItemEntityId implements Serializable {
    private Long productId;
    private Long invoiceId;

    public ItemEntityId(Long productId, Long invoiceId) {
        this.productId = productId;
        this.invoiceId = invoiceId;
    }

    public ItemEntityId() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }
}
