package pwr.olek.zpo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pwr.olek.zpo.model.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByNameContainsIgnoreCase(String string);

    List<Product> findByCategoryNameContainsIgnoreCase(String string);

    List<Product> findByOrderByNameAsc();

    List<Product> findByOrderByNameDesc();

    List<Product> findByOrderByCategoryAsc();

    List<Product> findByOrderByCategoryDesc();

    List<Product> findByOrderByPriceAsc();

    List<Product> findByOrderByPriceDesc();

    List<Product> findByCategory_Id(Long id);


}
