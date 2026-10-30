package com.pedrorodrigo.financehub.budget;

import com.pedrorodrigo.financehub.shared.persistence.AuditableEntity;
import com.pedrorodrigo.financehub.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "budgets")
public class Budget extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "budget_date", nullable = false)
    private LocalDate budgetDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Budget(User user, LocalDate budgetDate) {
        if (user == null) {
            throw new IllegalArgumentException("User need to exist");
        }

        this.user = user;
        this.budgetDate = normalizeBudgetDate(budgetDate);
    }

    private LocalDate normalizeBudgetDate(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("Budget date is required");
        }

        return date.withDayOfMonth(1);
    }
}
