package pl.spring.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.spring.demo.entity.ItemEntity;
import pl.spring.demo.entity.ItemEntityId;

/**
 * Created by Paweł on 28.01.2016.
 */
public interface ItemRepository extends JpaRepository<ItemEntity, ItemEntityId> {


    @Query("from ItemEntity i where i.invoiceId = :invoice_id")
    List<ItemEntity> findByInvoiceId(@Param("invoice_id")Long invoice_id);

    @Query("from ItemEntity i where i.productId = :product_id")
    List<ItemEntity> findByProductId(@Param("product_id") Long product_id);

}
