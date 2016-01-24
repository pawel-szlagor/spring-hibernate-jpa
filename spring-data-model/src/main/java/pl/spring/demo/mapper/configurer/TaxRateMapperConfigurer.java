package pl.spring.demo.mapper.configurer;

import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;
import pl.spring.demo.entity.TaxRateEntity;
import pl.spring.demo.mapper.MappingConfigurer;
import pl.spring.demo.to.TaxRateTo;

@Component
public class TaxRateMapperConfigurer implements MappingConfigurer {

    @Override
    public void configure(MapperFactory mapperFactory) {
        mapperFactory.classMap(TaxRateEntity.class, TaxRateTo.class).byDefault().register();
    }
}
