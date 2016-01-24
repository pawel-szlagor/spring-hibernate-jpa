package pl.spring.demo.to;

/**
 * Created by Paweł on 18.01.2016.
 */

public class TaxRateTo {

    private Long id;
    private Double value;

    public TaxRateTo() {
    }

    public TaxRateTo(Long id, Double value) {
        this.id = id;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
