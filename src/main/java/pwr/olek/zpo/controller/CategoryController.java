package pwr.olek.zpo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pwr.olek.zpo.model.Category;
import pwr.olek.zpo.repository.CategoryRepository;

@Controller
public class CategoryController {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/addCategory")
    public String addForm(Model model) {
        model.addAttribute("category", new Category());
        return "addcategory";

    }

    @PostMapping("/saveCategory")
    public String addCategory(Category category) {
        categoryRepository.save(category);
        return "redirect:/";
    }
}
