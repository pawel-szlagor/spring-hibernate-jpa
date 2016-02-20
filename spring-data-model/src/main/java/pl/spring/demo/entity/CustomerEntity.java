package pl.spring.demo.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "CUSTOMER")
@PrimaryKeyJoinColumn(name = "company_id", referencedColumnName = "id")
public class CustomerEntity extends CompanyEntity implements Serializable {

    @OneToMany(fetch = FetchType.EAGER)
    private Set<InvoiceEntity> invoices;

    public Set<InvoiceEntity> getInvoices() {
        return invoices;
    }

    public void setInvoices(Set<InvoiceEntity> invoices) {
        this.invoices = invoices;
    }
}
