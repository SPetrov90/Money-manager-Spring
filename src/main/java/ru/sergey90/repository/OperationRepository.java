package ru.sergey90.repository;

import ru.sergey90.entity.Cost;
import ru.sergey90.entity.Income;
import ru.sergey90.entity.Operation;

import java.time.LocalDateTime;
import java.util.List;


public interface OperationRepository <T extends Operation>{

    T save(T operation, int userId);

    boolean delete(int id, int userId);

    T get(int id, int userId);

    List<T> getAll(int userId);

    List<Income> getAllIncome(int userId);

    List<Cost> getAllCost(int userId);

    void deleteAll(int userId);

    List<Income> getIncomeBetween(LocalDateTime startDate, LocalDateTime endDate, int userId);

    List<T> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId);

    List<Cost> getCostBetween(LocalDateTime startDate, LocalDateTime endDate, int userId);
}
