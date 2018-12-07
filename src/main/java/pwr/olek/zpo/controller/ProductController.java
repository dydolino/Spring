package pwr.olek.zpo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/name/asc")
    public String sortByNameASC(Model model) {

        List<Product> products = productRepository.findByOrderByNameAsc();

        model.addAttribute("products", products);
        return "allproducts";
    }

    @GetMapping("/name/desc")
    public String sortByNameDESC(Model model) {

        List<Product> products = productRepository.findByOrderByNameDesc();

        model.addAttribute("products", products);
        return "allproducts";
    }

    @GetMapping("/price/asc")
    public String sortByPriceASC(Model model) {

        List<Product> products = productRepository.findByOrderByPriceAsc();

        model.addAttribute("products", products);
        return "allproducts";
    }

    @GetMapping("/price/desc")
    public String sortByPriceDESC(Model model) {

        List<Product> products = productRepository.findByOrderByPriceDesc();

        model.addAttribute("products", products);
        return "allproducts";
    }

    @GetMapping("/category/asc")
    public String sortByCategoryASC(Model model) {

        List<Product> products = productRepository.findByOrderByCategoryAsc();

        model.addAttribute("products", products);
        return "allproducts";
    }

    @GetMapping("/category/desc")
    public String sortByCategoryDESC(Model model) {

        List<Product> products = productRepository.findByOrderByCategoryDesc();

        model.addAttribute("products", products);
        return "allproducts";
    }

    @PostMapping("/search")
    public String search(@RequestParam String string, @RequestParam String query, Model model) {

        if (query.equals("name")) {
            List<Product> allProducts = productRepository.findByNameContainsIgnoreCase(string);
            if (!allProducts.isEmpty()) {
                model.addAttribute("products", allProducts);
            }
        } else {
            List<Product> allProducts = productRepository.findByCategoryNameContainsIgnoreCase(string);
            if (!allProducts.isEmpty()) {
                model.addAttribute("products", allProducts);
            }
        }

        return "searchproduct";
    }


}
