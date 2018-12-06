package pwr.olek.zpo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pwr.olek.zpo.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {


}
