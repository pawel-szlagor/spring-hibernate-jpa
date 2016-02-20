package pl.spring.demo.service.impl;

import java.util.List;

import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.spring.demo.entity.ItemEntity;
import pl.spring.demo.entity.ItemEntityId;
import pl.spring.demo.repository.ItemRepository;
import pl.spring.demo.service.ItemService;
import pl.spring.demo.to.ItemTo;

/**
 * Created by Paweł on 24.01.2016.
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private MapperFacade mapper;


    @Override
    public List<ItemTo> findByInvoiceId(Long invoice_id) {
        List<ItemEntity> itemEntity = itemRepository.findByInvoiceId(invoice_id);
        return mapper.mapAsList(itemEntity, ItemTo.class);
    }

    @Override
    public List<ItemTo> findByProductId(Long product_id) {
        List<ItemEntity> itemEntity = itemRepository.findByProductId(product_id);
        return mapper.mapAsList(itemEntity, ItemTo.class);
    }

    @Override
    public List<ItemTo> findAllItems() {
        List<ItemEntity> itemEntity = itemRepository.findAll();
        return mapper.mapAsList(itemEntity, ItemTo.class);
    }

    @Override
    public ItemTo findById(ItemEntityId id) {
        ItemEntity itemEntity = itemRepository.findOne(id);
        return mapper.map(itemEntity, ItemTo.class);
    }
}
