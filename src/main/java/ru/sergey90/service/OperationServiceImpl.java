package ru.sergey90.service;

import ru.sergey90.entity.Cost;
import ru.sergey90.entity.Income;
import ru.sergey90.entity.Operation;

import java.time.LocalDateTime;
import java.util.List;

public class OperationServiceImpl implements OperationService {
    @Override
    public Operation get(int id, int userId) {
        return null;
    }

    @Override
    public void delete(int id, int userId) {

    }

    @Override
    public List<Operation> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return null;
    }

    @Override
    public List<Income> getIncomeBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return null;
    }

    @Override
    public List<Cost> getCostBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return null;
    }

    @Override
    public List getAll(int userId) {
        return null;
    }

    @Override
    public List<Income> getAllIncome(int userId) {
        return null;
    }

    @Override
    public List<Cost> getAllCost(int userId) {
        return null;
    }

    @Override
    public void deleteAll(int userId) {

    }

    @Override
    public Operation update(Operation operation, int userId) {
        return null;
    }

    @Override
    public Operation save(Operation operation, int userId) {
        return null;
    }
}
