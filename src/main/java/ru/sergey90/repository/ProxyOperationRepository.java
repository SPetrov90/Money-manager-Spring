package ru.sergey90.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.sergey90.entity.Cost;
import ru.sergey90.entity.Income;
import ru.sergey90.entity.Operation;

import java.time.LocalDateTime;
import java.util.List;

public interface ProxyOperationRepository extends JpaRepository<Operation, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Operation m WHERE m.id=:id AND m.user.id=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);

    @Override
    Operation save(Operation item);

    @Query("SELECT m FROM Operation m WHERE m.id=:id AND m.user.id=:userId")
    Operation get(@Param("id") int id, @Param("userId") int userId);

    @Query("SELECT m FROM Operation m WHERE m.user.id=:userId ORDER BY m.dateTime DESC")
    List<Operation> getAll(@Param("userId") int userId);

    @Query("SELECT m FROM Operation m WHERE  m.user.id=:userId AND TYPE(m) = Income ORDER BY m.dateTime DESC")
    List<Income> getAllIncome(@Param("userId") int userId);

    @Query("SELECT m FROM Operation m WHERE  m.user.id=:userId AND TYPE(m) = Cost ORDER BY m.dateTime DESC")
    List<Cost> getAllCost(@Param("userId") int userId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Operation i WHERE i.user.id=:userId")
    void deleteAll(@Param("userId") int userId);

    @Query("SELECT m from Operation m WHERE m.user.id=:userId AND m.dateTime>=:after AND m.dateTime<:before ORDER BY m.dateTime DESC")
    List<Operation> getBetween(@Param("after") LocalDateTime startDate, @Param("before") LocalDateTime endDate, @Param("userId") int userId);

    @Query("SELECT m from Operation m WHERE m.user.id=:userId AND m.dateTime>=:after AND m.dateTime<:before AND TYPE(m) = Income  ORDER BY m.dateTime DESC")
    List<Income> getIncomeBetween(@Param("after") LocalDateTime startDate, @Param("before") LocalDateTime endDate, @Param("userId") int userId);

    @Query("SELECT m from Operation m WHERE m.user.id=:userId AND m.dateTime>=:after AND m.dateTime<:before AND TYPE(m) = Income  ORDER BY m.dateTime DESC")
    List<Cost> getCostBetween(@Param("after") LocalDateTime startDate, @Param("before") LocalDateTime endDate, @Param("userId") int userId);

}


