package pwr.olek.zpo.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
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
import pwr.olek.zpo.service.CategoryService;

import java.util.List;
import java.util.Optional;

@Controller
public class CategoryController {

    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;
    private CategoryService categoryService;


    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Autowired
    public CategoryController(CategoryRepository categoryRepository, ProductRepository productRepository, CategoryService categoryService) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    @GetMapping("/addCategory")
    public String addForm(Model model) {
        model.addAttribute("category", new Category());
        return "addcategory";

    }

    @GetMapping("/deletecategory/{id}")
    public String delete(@PathVariable Long id) {
        categoryRepository.deleteById(id);
        return "redirect:/allcategories";
    }

    @PostMapping("/savecategory")
    public String addCategory(Category category) {
        categoryRepository.save(category);
        return "redirect:/";
    }

    @GetMapping("/allcategories")
    public String allCategories(Model model) {
        List<Category> allCategories = categoryRepository.findAll();
        model.addAttribute("categories", allCategories);

        return "allcategories";
    }

    @GetMapping("/category/{id}")
    public String singleCategory(Model model, @PathVariable Long id) {

        Optional<Category> categoryById = categoryRepository.findById(id);
        if (categoryById.isPresent()) {
            model.addAttribute("category", categoryById.get());
            model.addAttribute("id",id);
        }

        return "editcategory";
    }

    @PostMapping("/update/{id}")
    public String update(Category category,@PathVariable Long id){

        categoryService.update(id, category);
        return "redirect:/allcategories";
    }
}
