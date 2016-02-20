package pl.spring.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.spring.demo.entity.CustomerEntity;

/**
 * Created by Paweł on 28.01.2016.
 */
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {


    @Query("from CustomerEntity c where c.name like :name%")
    List<CustomerEntity> findByNameLike(@Param("name") String name);

    @Query("from CustomerEntity c where c.NIP= :NIP")
    List<CustomerEntity> findByNIP(@Param("NIP") String NIP);

    @Query("from CustomerEntity c where c.city= :cityName")
    List<CustomerEntity> findByCityName(@Param("cityName") String cityName);

}
