package pl.spring.demo.dao;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.spring.demo.entity.AddressEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonDaoTest-context.xml")
public class AddressDaoTest {

    @Autowired
    private AddressDao addressDao;

    @Test
    public void testShouldFindAddressessByStreetname() {
        // given
        final String streetName = "testStreet";
        // when
        List<AddressEntity> addressEntities = addressDao.findByStreet(streetName);
        // then
        assertNotNull(addressEntities);
        assertFalse(addressEntities.isEmpty());
    }
}