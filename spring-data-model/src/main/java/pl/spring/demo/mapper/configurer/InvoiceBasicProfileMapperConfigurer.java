package pl.spring.demo.mapper.configurer;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import org.springframework.stereotype.Component;
import pl.spring.demo.mapper.MappingConfigurer;
import pl.spring.demo.to.InvoiceBasicProfileTo;
import pl.spring.demo.to.InvoiceTo;

@Component
public class InvoiceBasicProfileMapperConfigurer implements MappingConfigurer {

    @Override
    public void configure(MapperFactory mapperFactory) {
        mapperFactory.classMap(InvoiceTo.class, InvoiceBasicProfileTo.class)
                .customize(new CustomMapper<InvoiceTo, InvoiceBasicProfileTo>() {
                    @Override
                    public void mapAtoB(InvoiceTo invoiceTo, InvoiceBasicProfileTo invoiceBasicProfileTo, MappingContext context) {
                       invoiceBasicProfileTo.setid(invoiceTo.getid());
                    }
                })
                .field("saleDate", "saleDate")
                .field("creationDate", "creationDate")
                .field("paymentDeadline", "paymentDeadline")
                .field("customer", "customer")
                .field("items", "items")
                .field("totalNetto", "totalNetto")
                .register();
    }
}
