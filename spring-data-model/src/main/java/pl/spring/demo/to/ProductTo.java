package pl.spring.demo.to;

import org.joda.money.Money;
import pl.spring.demo.enums.MeasureUnits;

/**
 * Created by Paweł on 18.01.2016.
 */
public class ProductTo {

    private Long id;
    private String name;
    private String pkwiu;
    private MeasureUnits measureUnit;
    private Money unitPriceNetto;
    private TaxRateTo taxRate;

    protected ProductTo() {
    }

    public ProductTo(Long id, String name, String pkwiu, MeasureUnits measureUnit, Money unitPriceNetto, TaxRateTo taxRate) {
        this.id = id;
        this.name = name;
        this.pkwiu = pkwiu;
        this.measureUnit = measureUnit;
        this.unitPriceNetto = unitPriceNetto;
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

    public TaxRateTo getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(TaxRateTo taxRate) {
        this.taxRate = taxRate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t(").append(id).append(")\t").append(name).append("\t")
                .append(unitPriceNetto).append("\u20ac\tvat ").append(taxRate.getValue()).append("%");
        return sb.toString();
    }

}
