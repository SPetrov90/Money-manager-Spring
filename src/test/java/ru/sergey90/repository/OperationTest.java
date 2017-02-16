package ru.sergey90.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ru.sergey90.config.DataConfig;
import ru.sergey90.config.WebConfig;
import ru.sergey90.entity.*;
import ru.sergey90.service.UserService;

import java.time.LocalDateTime;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {DataConfig.class, WebConfig.class})
public class OperationTest {

    @Autowired
    private ProxyOperationRepository repository;
    @Autowired
    private UserService userService;

    public void addNewIncome() {
        Income income = new Income();
        income.setIncomeType(IncomeType.GIFT);
        income.setDateTime(LocalDateTime.now());
        income.setUser(userService.get(100000));
        income.setDescription("подарок");
        income.setMoneyCount(777);
        repository.save(income);
        userService.updateBalance(userService.get(100000).getId(), userService.get(100000).getBalance() + income.getMoneyCount() );
    }

    public void addNewCost() {
        Cost cost = new Cost();
        cost.setCostType(CostType.FOOT);
        cost.setDateTime(LocalDateTime.now());
        cost.setUser(userService.get(100000));
        cost.setDescription("I eating now");
        cost.setMoneyCount(300);
        repository.save(cost);
        userService.updateBalance(userService.get(100000).getId(), userService.get(100000).getBalance() - cost.getMoneyCount());
    }
    @Test
    public void getAll(){
        List<Operation> all = repository.getAll(100000);
        for (Operation op: all){
            System.out.println(op);
        }
    }

    @Test
    public void getAllIncome(){
        List<Income> all = repository.getAllIncome(100000);
        for (Income op: all){
            System.out.println(op);
        }
    }

    @Test
    public void getAllCost(){
        List<Cost> all = repository.getAllCost(100000);
        for (Cost op: all){
            System.out.println(op);
        }
    }

}
