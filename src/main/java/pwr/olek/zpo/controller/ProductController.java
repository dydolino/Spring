package pwr.olek.zpo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pwr.olek.zpo.model.Product;
import pwr.olek.zpo.repository.ProductRepository;

import java.util.List;

@Controller
public class ProductController {

    private ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/addProduct")
    public String addForm(Model model){
        model.addAttribute("product",new Product());
        return "addproduct";

    }

    @PostMapping("/save")
    public String addProduct(Product product){
        productRepository.save(product);
        return "success";
    }

    @GetMapping("/allproducts")
    public String allProducts(Model model){
        List<Product> allProducts=productRepository.findAll();
        model.addAttribute("products",allProducts);
        return "allproducts";
    }
}
