package pl.spring.demo.mapper.configurer;

import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;
import pl.spring.demo.entity.ProductEntity;
import pl.spring.demo.mapper.MappingConfigurer;
import pl.spring.demo.to.ProductTo;

@Component
public class ProductMapperConfigurer implements MappingConfigurer {

    @Override
    public void configure(MapperFactory mapperFactory) {
        mapperFactory.classMap(ProductEntity.class, ProductTo.class).byDefault().register();
    }
}
