package pwr.olek.zpo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pwr.olek.zpo.model.Category;
import pwr.olek.zpo.model.Product;
import pwr.olek.zpo.repository.CategoryRepository;
import pwr.olek.zpo.repository.ProductRepository;
import pwr.olek.zpo.service.ProductService;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {

    private ProductRepository productRepository;
    private ProductService productService;
    private CategoryRepository categoryRepository;

    @Autowired
    public ProductController(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }


    @GetMapping("/")
    public String main() {
        return "main";
    }

    @GetMapping("/addProduct")
    public String addForm(Model model) {
        model.addAttribute("product", new Product());
        List<Category> allCategories = categoryRepository.findAll();
        model.addAttribute("categories", allCategories);
        return "addproduct";

    }

    @PostMapping("/save")
    public String addProduct(Product product) {
        productRepository.save(product);
        return "redirect:/allproducts";
    }

    @GetMapping("/allproducts")
    public String allProducts(Model model) {
        List<Product> allProducts = productRepository.findAll();
        model.addAttribute("products", allProducts);

        return "allproducts";
    }

    @GetMapping("/edit/{id}")
    public String singleProduct(Model model, @PathVariable Long id) {

        List<Category> allCategories = categoryRepository.findAll();
        model.addAttribute("categories", allCategories);
        Optional<Product> productByID = productRepository.findById(id);
        if (productByID.isPresent()) {

            model.addAttribute("product", productByID.get());
        }

        return "single";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        productRepository.deleteById(id);
        return "redirect:/allproducts";
    }

    @GetMapping("/update")
    public String update() {

        productService.update(2L, "Łoś");
        return "";
    }


}
