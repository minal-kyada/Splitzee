package com.tripsplit.service;

import com.tripsplit.entity.Expense;
import com.tripsplit.entity.User;
import com.tripsplit.exception.ExpenseException;
import com.tripsplit.model.ExpenseModel;
import com.tripsplit.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ExpenseServiceImp implements ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Override
    public Expense createExpense(ExpenseModel expenseModel) throws ExpenseException {
        Expense expense = new Expense();
        String expName = expenseModel.getExpName();
        String stringRegex = "(?i)(^[a-z])((?![ .,'-]$)[a-z .,'-]){0,24}$";
        Matcher expNameMatch = Pattern.compile(stringRegex).matcher(expName);
        if (expName.isEmpty() || expName.isBlank() || !expNameMatch.matches()) {
            throw new ExpenseException("Invalid Expense Name");
        }
        expense.setExpName(expName);
        expense.setExpAmt(expenseModel.getExpAmt());

        List<User> userList = expenseModel.getUsrSplitBtw();
        if (userList.isEmpty()) {
            throw new ExpenseException("No expense users found in this group");
        }
        expense.setUsrSplitBtw(expenseModel.getUsrSplitBtw());
        expense.setExpPaidBy(expenseModel.getExpPaidBy());
        expense.setExpGrp(expenseModel.getExpGrp());
        expenseRepository.save(expense);
        return expense;
    }

    @Override
    public List<Expense> getGrpExpenses(Long groupId) {
        return null;
    }

    @Override
    public String resolveExpense(Long expId) {
        expenseRepository.deleteById(expId);
        return "expense resolved";
    }
}
