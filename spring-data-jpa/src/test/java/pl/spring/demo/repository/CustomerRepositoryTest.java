package pl.spring.demo.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
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

    private Session session;

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

    @Test
    public void shouldAddNewCustomer(){
        //given
        final CustomerEntity customer = new CustomerEntity();
        final String ACCOUNT = "AccountTest";
        final String CITY = "CityTest";
        final String E_MAIL = "EMailTest";
        final String NAME = "NameTest";
        final String NIP = "NIP";
        final String POST_CODE = "01234";
        customer.setAccount(ACCOUNT);
        customer.setCity(CITY);
        customer.seteMail(E_MAIL);
        customer.setName(NAME);
        customer.setNIP(NIP);
        customer.setPostCode(POST_CODE);
        //when
        customerRepository.save(customer);
        //then
        assertTrue(customerRepository.findAll().contains(customer));
    }

    @Test
    public void shouldDeleteAddedCustomer(){
        //given
        final CustomerEntity customer = new CustomerEntity();
        final String ACCOUNT = "AccountTest";
        final String CITY = "CityTest";
        final String E_MAIL = "EMailTest";
        final String NAME = "NameTest";
        final String NIP = "NIPTest";
        final String POST_CODE = "01234";
        customer.setAccount(ACCOUNT);
        customer.setCity(CITY);
        customer.seteMail(E_MAIL);
        customer.setName(NAME);
        customer.setNIP(NIP);
        customer.setPostCode(POST_CODE);
        customerRepository.save(customer);
        //when
        customerRepository.delete(customer.getId());
        //then
        assertFalse(customerRepository.findAll().contains(customer));
    }

    @Test
    public void shouldUpdateCustomerByCopyingWithIncreasedVersion(){
        //given
        CustomerEntity customer = customerRepository.findOne(1L);
        customer.setName("NewName");
        Long previousVersion = customer.getVersion();
        //when
        customerRepository.save(customer);
        //then
        assertEquals(previousVersion +1, customerRepository.findOne(1L).getVersion());
        AuditReader reader = AuditReaderFactory.get(session);
        CustomerEntity customerRevisionOne = reader.findRevision(CustomerEntity.class, 1L);
        assertEquals(customer, customerRevisionOne);
    }

}