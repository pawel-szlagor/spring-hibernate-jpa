package pl.spring.demo.to;

import pl.spring.demo.enums.MeasureUnits;

/**
 * Created by Paweł on 18.01.2016.
 */
public class ProductTo {

    private Long id;
    private String name;
    private String pkwiu;
    private MeasureUnits measureUnit;
    private double quantity;
    private double unitPriceNetto;
    private double taxRate;

    protected ProductTo() {
    }

    public ProductTo(Long id, String name, String pkwiu, MeasureUnits measureUnit, double quantity, double unitPriceNetto, double taxRate) {
        this.id = id;
        this.name = name;
        this.pkwiu = pkwiu;
        this.measureUnit = measureUnit;
        this.quantity = quantity;
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

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getUnitPriceNetto() {
        return unitPriceNetto;
    }

    public void setUnitPriceNetto(double unitPriceNetto) {
        this.unitPriceNetto = unitPriceNetto;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }
}
