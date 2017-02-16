package ru.sergey90.service;

import ru.sergey90.entity.Operation;

import java.time.LocalDateTime;
import java.util.List;

public interface OperationService <T extends Operation> {
    T get(int id, int userId);

    void delete(int id, int userId);

    List<Operation> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId);

    List<T> getIncomeBetween(LocalDateTime startDate, LocalDateTime endDate, int userId);

    List<T> getCostBetween(LocalDateTime startDate, LocalDateTime endDate, int userId);

    List<T> getAll(int userId);

    List<T> getAllIncome(int userId);

    List<T> getAllCost(int userId);

    void deleteAll(int userId);

    T update(T operation, int userId);

    T save(T operation, int userId);
}
