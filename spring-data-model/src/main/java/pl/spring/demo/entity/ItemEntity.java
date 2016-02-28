package pl.spring.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.math.RoundingMode;

import org.joda.money.Money;

/**
 * Created by Paweł on 29.01.2016.
 */
@Entity
@Table(name = "ITEM")
@IdClass(ItemEntityId.class)
public class ItemEntity implements Serializable {

    @Column(nullable = false, precision = 2, scale = 6)
    private Double quantity;

    @Id
    @Column(name = "INVOICE_ID", insertable = false, updatable = false)
    private Long invoiceId;

    @Id
    @Column(name = "PRODUCT_ID", insertable = false, updatable = false)
    private Long productId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "INVOICE_ID", nullable = false)
    private InvoiceEntity invoice;

    @OneToOne(optional = false)
    @JoinColumn(name = "PRODUCT_ID")
    private ProductEntity product;

    @Transient
    public Money getTotalNetto(){
        return product.getUnitPriceNetto().multipliedBy(quantity, RoundingMode.HALF_UP);
        //return Money.zero(CurrencyUnit.EUR);
    }

    @Transient
    public Money getTaxValue(){
        return getTotalNetto().multipliedBy(product.getTaxRate().getValue() * 0.01, RoundingMode.HALF_UP);
        //return Money.zero(CurrencyUnit.EUR);
    }

    @Transient
    public Money getTotalGross(){
        return getTotalNetto().plus(getTaxValue());
        //return Money.zero(CurrencyUnit.EUR);
    }

    public double getQuantity() {
        return quantity;
    }

    public InvoiceEntity getInvoice() {
        return invoice;
    }

    public void setInvoice(InvoiceEntity invoice) {
        this.invoice = invoice;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
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

    public ItemEntity() {
    }

    public ItemEntity(Double quantity, InvoiceEntity invoice, ProductEntity product) {
        this.quantity = quantity;
        this.invoice = invoice;
        this.product = product;
        this.invoiceId = invoice.getId();
        this.productId = product.getId();
    }
}
