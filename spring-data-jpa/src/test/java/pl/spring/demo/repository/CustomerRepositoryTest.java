package pl.spring.demo.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.spring.demo.entity.CustomerEntity;

/**
 * Created by Paweł on 28.01.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonRepositoryTest-context.xml")
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void testShouldFindCompanyById() {
        // given
        final long companyId = 1;
        // when
        CustomerEntity customerEntity = customerRepository.findOne(companyId);
        // then
        assertNotNull(customerEntity);
        Assert.assertEquals("6680009781", customerEntity.getNIP());
    }


    @Test
    public void testShouldFindCompanyByNIP() {
        // given
        final String companyNIP = "6680009781";
        // when
        List<CustomerEntity> customerEntities = customerRepository.findByNIP(companyNIP);
        // then
        assertTrue(customerEntities.size() > 0);
        Assert.assertTrue(customerEntities.stream().allMatch(customerEntity -> customerEntity.getNIP().equals(companyNIP)));
    }

    @Test
    public void testShouldFindCompanyByNameLike() {
        // given
        final String companyName = "first";
        // when
        List<CustomerEntity> customerEntities = customerRepository.findByNameLike(companyName);
        // then
        assertTrue(customerEntities.size() > 0);
        Assert.assertTrue(customerEntities.stream().allMatch(customerEntity -> customerEntity.getName().matches(companyName + "(.)*")));
    }

}