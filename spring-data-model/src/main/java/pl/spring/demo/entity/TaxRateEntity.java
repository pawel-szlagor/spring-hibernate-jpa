package pl.spring.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Paweł on 18.01.2016.
 */
@Entity
@Table(name = "TAX_RATE")
public class TaxRateEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, precision = 2, scale = 4, name = "TAX_RATE_VALUE")
    private double value;
}
