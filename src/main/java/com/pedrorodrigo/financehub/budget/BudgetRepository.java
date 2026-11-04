package com.pedrorodrigo.financehub.budget;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {

    Optional<Budget> findByUser_IdAndBudgetDate(
            Long userId,
            LocalDate budgetDate
    );

    boolean existsByUser_IdAndBudgetDate(
            Long userId,
            LocalDate budgetDate
    );

    Page<Budget> findByUser_Id(
            Long userId,
            Pageable pageable
    );
}
