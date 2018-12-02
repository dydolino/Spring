package pwr.olek.zpo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pwr.olek.zpo.model.Product;
import pwr.olek.zpo.repository.ProductRepository;

import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public void update(Long id, String name){

        Optional<Product> byId = productRepository.findById(id);
        Product product=byId.get();
        product.setName(name);
    }
}
