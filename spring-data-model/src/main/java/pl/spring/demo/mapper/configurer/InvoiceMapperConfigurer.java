package pl.spring.demo.mapper.configurer;

import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;
import pl.spring.demo.entity.InvoiceEntity;
import pl.spring.demo.mapper.MappingConfigurer;
import pl.spring.demo.to.InvoiceTo;

@Component
public class InvoiceMapperConfigurer implements MappingConfigurer {

    @Override
    public void configure(MapperFactory mapperFactory) {
        mapperFactory.classMap(InvoiceEntity.class, InvoiceTo.class).byDefault().register();
    }
}
