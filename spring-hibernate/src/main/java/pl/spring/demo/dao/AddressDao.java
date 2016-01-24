package pl.spring.demo.dao;

import java.util.List;

import pl.spring.demo.entity.AddressEntity;

/**
 * Created by Paweł on 22.01.2016.
 */
public interface AddressDao {

    List<AddressEntity> findByStreet(String name);

    List<AddressEntity> findByCity(String city);

    List<AddressEntity> findByCityAndStreetAndStreetNumber(String name, String city, String streetNumber);

    List<AddressEntity> findAll();
}
