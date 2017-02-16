package ru.sergey90.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.sergey90.entity.BaseEntity;
import ru.sergey90.entity.User;
import ru.sergey90.repository.ProxyUserRepository;
import ru.sergey90.repository.UserRepository;
import ru.sergey90.util.exteption.ValidationException;
import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Repository
public class DataJpaUserRepositoryImpl implements UserRepository {
    private static final Sort SORT_NAME_EMAIL = new Sort("name", "email");

    @Autowired
    private ProxyUserRepository proxy;

    @Autowired
    private Environment env;

    public void checkModificationAllowed(Integer id) {
        if (id != null && id < BaseEntity.START_SEQ + 2) {
            throw new ValidationException("Admin/User modification is not allowed. <br><br><a class=\"btn btn-primary btn-lg\" role=\"button\" href=\"register\">Register &raquo;</a> your own please.");
        }
    }

    @Override
    public User save(User user) {
        checkModificationAllowed(user.getId());
        return proxy.save(user);
    }

    @Override
    public boolean delete(int id) {
        checkModificationAllowed(id);
        return proxy.delete(id) != 0;
    }

    @Override
    public User get(int id) {
        return proxy.findOne(id);
    }

    @Override
    public User getByEmail(String email) {
        return proxy.getByEmail(email);
    }

    @Override
    public List<User> getAll() {
        return proxy.findAll(SORT_NAME_EMAIL);
    }

    @Override
    public int getBalance(int id) {
       return proxy.getBananceByUserId(id);
    }

    @Override
    public int updateBalance(int id, int count) {
        return proxy.setBananceByUserId(id, count);
    }

    @Override
    public void enable(int id, boolean enabled) {
        checkModificationAllowed(id);
        proxy.enable(id, enabled);
    }
}
