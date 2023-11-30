package com.tripsplit.controller;

import com.tripsplit.entity.Expense;
import com.tripsplit.exception.ExpenseException;
import com.tripsplit.model.ExpenseModel;
import com.tripsplit.service.ExpenseService;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expense")
@CrossOrigin(origins = "*")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;
    @Autowired
    private MeterRegistry meterRegistry;

    @PostMapping("/create")
    public Expense createExpense(@RequestBody ExpenseModel expenseModel){

        try {
            return expenseService.createExpense(expenseModel);
        } catch (ExpenseException e) {
            meterRegistry.counter("CreateExpenseErrorCounter","error_message", e.getMessage()).increment();
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/group/{id}")
    public List<Expense> getGrpExpenses(@PathVariable("id") Long groupId){
        return expenseService.getGrpExpenses(groupId);
    }
    @GetMapping("/resolve/{id}")
    public String resolveExpense(@PathVariable("id") Long expId){
        return expenseService.resolveExpense(expId);
    }
}
