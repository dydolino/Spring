package pwr.olek.zpo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pwr.olek.zpo.model.Category;
import pwr.olek.zpo.repository.CategoryRepository;

import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Transactional
    public void update(Long id, Category category){

        Optional<Category> byId = categoryRepository.findById(id);
        Category tempCategory=byId.get();
        tempCategory.setName(category.getName());
        tempCategory.setDescription(category.getDescription());


    }
}
