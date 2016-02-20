package pl.spring.demo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.google.common.collect.Lists;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

/**
 * Created by Paweł on 18.01.2016.
 */
@Entity
@Table(name = "INVOICE")
public class InvoiceEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 100)
    private Date saleDate;

    @Column(nullable = false)
    private Date creationDate;

    @Column(nullable = false)
    private Date paymentDeadline;

    @Column(nullable = true)
    private String remarks;

    @ManyToOne(optional = false)
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    private CustomerEntity customer;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "invoice")
    private List<ItemEntity> items;

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

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public List<ItemEntity> getItems() {
        return items;
    }

    public void setItems(List<ItemEntity> items) {
        this.items = items;
    }

    @Transient
    public Money getTotalNetto() {
        Optional<Money> sum = items.stream().map(ItemEntity::getTotalNetto).reduce((i, j) -> i.plus(j));
        return sum.isPresent() ? sum.get() : Money.zero(CurrencyUnit.registerCurrency("PLN", 48, 2, Lists.newArrayList("48")));
        //Money totalNetto = Money.zero(CurrencyUnit.registerCurrency("PLN", 48, 2, Lists.newArrayList("48")));
        //for (ItemEntity item : items)

        //totalNetto.plus(item.getProduct().getUnitPriceNetto().multipliedBy(item.getQuantity(), RoundingMode.HALF_UP));
    }
}
