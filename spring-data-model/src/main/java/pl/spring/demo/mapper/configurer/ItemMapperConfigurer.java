package pl.spring.demo.mapper.configurer;

import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;
import pl.spring.demo.entity.ItemEntity;
import pl.spring.demo.mapper.MappingConfigurer;
import pl.spring.demo.to.ItemTo;

@Component
public class ItemMapperConfigurer implements MappingConfigurer {

    @Override
    public void configure(MapperFactory mapperFactory) {
        mapperFactory.classMap(ItemEntity.class, ItemTo.class)
                .byDefault().register();
    }
}
