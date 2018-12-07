package pwr.olek.zpo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pwr.olek.zpo.model.Category;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {


}
