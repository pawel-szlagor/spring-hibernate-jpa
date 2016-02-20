package pl.spring.demo.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import pl.spring.demo.to.CustomerTo;
import pl.spring.demo.validation.CustomerValidator;

/**
 * Created by Paweł on 25.01.2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class CustomerValidatorTest {

    @InjectMocks
    private CustomerValidator customerValidator;
    @Mock
    private CustomerTo customer;

    @Before
    public void setUp(){

    }

    @Test
    public void shouldThrowExcWhenNIPLengthGreaterThen9Digits() throws Exception {

        //given
        String tooLongNIP = "1234567890";
        String customREGON = "1234567890";
        Mockito.when(customer.getNIP()).thenReturn(tooLongNIP);
        Mockito.when(customer.getREGON()).thenReturn(customREGON);
        //when
        /*Set<ConstraintViolation<CustomerTo>> violation = validator.validate(customer);*/
        //then

    }
}