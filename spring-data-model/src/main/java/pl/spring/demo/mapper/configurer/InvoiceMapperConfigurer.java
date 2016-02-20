package pl.spring.demo.mapper.configurer;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import org.springframework.stereotype.Component;
import pl.spring.demo.entity.InvoiceEntity;
import pl.spring.demo.mapper.MappingConfigurer;
import pl.spring.demo.to.InvoiceTo;

@Component
public class InvoiceMapperConfigurer implements MappingConfigurer {

    @Override
    public void configure(MapperFactory mapperFactory) {
        mapperFactory.classMap(InvoiceEntity.class, InvoiceTo.class)
        .customize(new CustomMapper<InvoiceEntity, InvoiceTo>() {
            @Override
            public void mapAtoB(InvoiceEntity invoiceEntity, InvoiceTo invoiceTo, MappingContext context) {
                invoiceTo.setTotalNetto(invoiceEntity.getTotalNetto());
            }
        })
                .byDefault()
                .register();
    }
}
