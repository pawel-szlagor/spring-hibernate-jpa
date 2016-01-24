package pl.spring.demo.entity;

import javax.persistence.*;
import java.io.Serializable;

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
    @Column(nullable = false, precision = 2, scale = 6)
    private double quantity;
    @Column(nullable = false, precision = 2, scale = 6)
    private double unitPriceNetto;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "TAX_RATE_VALUE")
    private TaxRateEntity taxRate;


}
