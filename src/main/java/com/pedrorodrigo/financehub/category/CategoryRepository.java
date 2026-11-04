package com.pedrorodrigo.financehub.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByUser_Id(Long userId);

    Optional<Category> findByUser_IdAndNameAndCategoryType(
            Long userId,
            String name,
            CategoryType categoryType
    );

    boolean existsByUser_IdAndNameAndCategoryType(
            Long userId,
            String name,
            CategoryType categoryType
    );
}
