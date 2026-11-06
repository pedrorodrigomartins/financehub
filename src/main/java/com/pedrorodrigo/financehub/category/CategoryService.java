package com.pedrorodrigo.financehub.category;

import com.pedrorodrigo.financehub.category.dto.CategoryResponse;
import com.pedrorodrigo.financehub.category.dto.CreateCategoryRequest;
import com.pedrorodrigo.financehub.user.User;
import com.pedrorodrigo.financehub.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Transactional
    public CategoryResponse createCategory(CreateCategoryRequest request, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "User with id " + userId + " not found."
                ));

        if (categoryRepository.existsByUser_IdAndNameAndCategoryType(
                user.getId(),
                request.name(),
                request.categoryType()
        )) {
            throw new IllegalArgumentException(
                    "Category with name " + request.name() +
                            " and type " + request.categoryType() +
                            " already exists for user with id " + user.getId()
            );
        }
        if (!user.isActive()) {
            throw new IllegalStateException(
                    "Cannot create category for an inactive user."
            );
        }

        Category category = new Category(
                request.name(),
                request.categoryType(),
                user
        );

        Category savedCategory = categoryRepository.save(category);
        return toCategoryResponse(savedCategory);
    }

    @Transactional(readOnly = true)
    public CategoryResponse getCategoryById(Long userId, Long categoryId) {
        Category category = categoryRepository.findByUser_IdAndId(userId, categoryId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Category with id " + categoryId + " not found for user with id " + userId
                ));

        return toCategoryResponse(category);
    }

    @Transactional(readOnly = true)
    public List<CategoryResponse> getCategoriesByUser(Long userId) {
        return categoryRepository.findByUser_Id(userId)
                .stream()
                .map(this::toCategoryResponse)
                .toList();
    }

    @Transactional
    public CategoryResponse updateCategory(CreateCategoryRequest request) {
        
    }

    private CategoryResponse toCategoryResponse(Category category) {
        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getCategoryType()
        );
    }
}
