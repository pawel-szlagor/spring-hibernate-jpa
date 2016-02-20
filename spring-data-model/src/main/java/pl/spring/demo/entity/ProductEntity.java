package pl.spring.demo.entity;

import javax.persistence.*;
import java.io.Serializable;

import org.hibernate.annotations.Columns;
import org.hibernate.annotations.Type;
import org.joda.money.Money;
import pl.spring.demo.enums.MeasureUnits;

/**
 * Created by Paweł on 18.01.2016.
 */
@Entity
@Table(name = "PRODUCT")
public class ProductEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = true)
    private String pkwiu;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MeasureUnits measureUnit;

    @Columns(columns = {@Column(name = "CURRENCY"), @Column(name = "AMOUNT") })
    @Type(type = "org.jadira.usertype.moneyandcurrency.joda.PersistentMoneyAmountAndCurrency")
/*    @Column(nullable = true, precision = 2, length = 10)*/
    private Money unitPriceNetto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "TAX_RATE_VALUE")
    private TaxRateEntity taxRate;

    public TaxRateEntity getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(TaxRateEntity taxRate) {
        this.taxRate = taxRate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPkwiu() {
        return pkwiu;
    }

    public void setPkwiu(String pkwiu) {
        this.pkwiu = pkwiu;
    }

    public MeasureUnits getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(MeasureUnits measureUnit) {
        this.measureUnit = measureUnit;
    }

    public Money getUnitPriceNetto() {
        return unitPriceNetto;
    }

    public void setUnitPriceNetto(Money unitPriceNetto) {
        this.unitPriceNetto = unitPriceNetto;
    }
}
