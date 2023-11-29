package com.tripsplit.service;

import com.tripsplit.entity.Expense;
import com.tripsplit.exception.ExpenseException;
import com.tripsplit.model.ExpenseModel;

import java.util.List;


public interface ExpenseService {
    Expense createExpense(ExpenseModel expenseModel) throws ExpenseException;

    List<Expense> getGrpExpenses(Long groupId);

    String resolveExpense(Long expId);
}
