package pl.spring.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.spring.demo.entity.InvoiceEntity;

public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long> {

}
