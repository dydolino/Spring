package pwr.olek.zpo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pwr.olek.zpo.model.Product;
import pwr.olek.zpo.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public List<Product> orderBy(){
        List<Product> byNameASC=productRepository.findByOrderByNameAsc();

        return byNameASC;
    }


}
