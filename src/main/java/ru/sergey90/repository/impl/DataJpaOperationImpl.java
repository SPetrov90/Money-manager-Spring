package ru.sergey90.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ru.sergey90.entity.Cost;
import ru.sergey90.entity.Income;
import ru.sergey90.entity.Operation;
import ru.sergey90.repository.OperationRepository;
import ru.sergey90.repository.ProxyOperationRepository;
import java.time.LocalDateTime;
import java.util.List;

public class DataJpaOperationImpl implements OperationRepository {

    @Autowired
    private ProxyOperationRepository repository;

    @Override
    public Operation save(Operation operation, int userId) {
        if (operation.getUser().getId() == userId) {
            return repository.save(operation);
        }
        return null;
    }

    @Override
    public boolean delete(int id, int userId) {
        int count =  repository.delete(id, userId);
        return count > 0;
    }

    @Override
    public Operation get(int id, int userId) {
        return repository.get(id, userId);
    }

    @Override
    public List<Operation> getAll(int userId) {
        return repository.getAll(userId);
    }

    @Override
    public List<Income> getAllIncome(int userId) {
        return repository.getAllIncome(userId);
    }

    @Override
    public List<Cost> getAllCost(int userId) {
        return repository.getAllCost(userId);
    }

    @Override
    public void deleteAll(int userId) {
        repository.deleteAll(userId);
    }

    @Override
    public List<Operation> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return repository.getBetween(startDate, endDate , userId);
    }

    @Override
    public List<Income> getIncomeBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return repository.getIncomeBetween(startDate, endDate , userId);
    }

    @Override
    public List<Cost> getCostBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return repository.getCostBetween(startDate, endDate , userId);
    }
}
