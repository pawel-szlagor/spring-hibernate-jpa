package pl.spring.demo.service;

import java.util.List;

import pl.spring.demo.entity.ItemEntityId;
import pl.spring.demo.to.ItemTo;

/**
 * Created by Paweł on 30.01.2016.
 */
public interface ItemService {
    List<ItemTo> findByInvoiceId(Long invoice_id);
    List<ItemTo> findByProductId(Long product_id);
    List<ItemTo> findAllItems();
    ItemTo findById(ItemEntityId id);
}
