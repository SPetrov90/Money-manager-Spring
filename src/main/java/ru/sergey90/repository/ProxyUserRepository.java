package ru.sergey90.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.sergey90.entity.User;

import java.util.List;

@Transactional(readOnly = true)
public interface ProxyUserRepository extends JpaRepository<User, Integer> {

    @Transactional
    @Modifying
//    @Query(name = User.DELETE)
    @Query("DELETE FROM User u WHERE u.id=:id")
    int delete(@Param("id") int id);

    @Override
    @Transactional
    User save(User user);

    @Override
    User findOne(Integer id);

    @Override
    List<User> findAll(Sort sort);

    User getByEmail(String email);

    @Query("SELECT u.balance FROM User u WHERE u.id=:id")
    int getBananceByUserId(@Param("id") int id);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.balance=:balance WHERE u.id=:id")
    int setBananceByUserId(@Param("id")int id,@Param("balance") int balance);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.enabled=:enabled WHERE u.id=:id")
    void enable(@Param("id") int id, @Param("enabled") boolean enabled);
}

