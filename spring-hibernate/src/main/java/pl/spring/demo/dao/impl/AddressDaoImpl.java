package pl.spring.demo.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import pl.spring.demo.dao.AddressDao;
import pl.spring.demo.entity.AddressEntity;

/**
 * Created by Paweł on 22.01.2016.
 */
@Repository
public class AddressDaoImpl extends AbstractDao<AddressEntity> implements AddressDao {
    @Override
    public List<AddressEntity> findByStreet(String street) {
        return getSession().createQuery("from AddressEntity a where a.street like :street").setString("street", street + "%").list();

    }

    @Override
    public List<AddressEntity> findByCity(String city) {
        return null;
    }

    @Override
    public List<AddressEntity> findByCityAndStreetAndStreetNumber(String name, String city, String streetNumber) {
        return null;
    }

    @Override
    public List<AddressEntity> findAll() {
        return null;
    }
}
