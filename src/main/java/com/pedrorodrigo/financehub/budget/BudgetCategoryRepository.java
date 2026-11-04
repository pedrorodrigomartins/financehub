package com.pedrorodrigo.financehub.budget;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface BudgetCategoryRepository
        extends JpaRepository<BudgetCategory, Long> {

    // 1. Listar os limites de um orçamento
    List<BudgetCategory> findByUser_IdAndBudget_Id(
            Long userId,
            Long budgetId,
            Pageable pageable
    );

    // 2. Buscar o limite de uma categoria em um orçamento
    Optional<BudgetCategory> findByUser_IdAndBudget_IdAndCategory_Id(
            Long userId,
            Long budgetId,
            Long categoryId
    );

    // 3. Verificar se uma categoria já existe no orçamento
    boolean existsByUser_IdAndBudget_IdAndCategory_Id(
            Long userId,
            Long budgetId,
            Long categoryId
    );

    // 4. Somar todos os limites de um orçamento
    @Query("""
        SELECT COALESCE(SUM(b.limitAmount), 0)
        FROM BudgetCategory b
        WHERE b.user.id = :userId
          AND b.budget.id = :budgetId
        """)
    BigDecimal sumLimitAmountByBudget(
            @Param("userId") Long userId,
            @Param("budgetId") Long budgetId
    );
}