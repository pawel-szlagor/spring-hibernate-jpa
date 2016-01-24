package pl.spring.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

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
    @ManyToOne(optional = true)
    @JoinColumn(name = "CUSTOMER_ID", nullable = true)
    private CustomerEntity customer;

}
