package pl.spring.demo.mapper.configurer;

import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;
import pl.spring.demo.entity.CustomerEntity;
import pl.spring.demo.mapper.MappingConfigurer;
import pl.spring.demo.to.CustomerTo;

@Component
public class CustomerMapperConfigurer implements MappingConfigurer {

    @Override
    public void configure(MapperFactory mapperFactory) {
        mapperFactory.classMap(CustomerEntity.class, CustomerTo.class).byDefault().register();
    }
}
