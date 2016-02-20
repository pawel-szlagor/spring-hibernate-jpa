package pl.spring.demo.mapper.configurer;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.metadata.TypeFactory;
import org.joda.money.Money;
import org.springframework.stereotype.Component;
import pl.spring.demo.mapper.MappingConfigurer;

@Component
public class MoneyMapperConfigurer implements MappingConfigurer {

    @Override
    public void configure(MapperFactory mapperFactory) {
        mapperFactory.registerObjectFactory((source, mappingContext) -> (Money) source,  TypeFactory.valueOf(Money.class));
    }
}
