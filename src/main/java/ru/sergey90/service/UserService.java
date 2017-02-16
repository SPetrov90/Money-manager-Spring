package ru.sergey90.service;

import ru.sergey90.entity.User;
import ru.sergey90.util.user.UserTo;
import ru.sergey90.util.exteption.NotFoundException;

import java.util.List;

public interface UserService {

    public User save(User user);

    int updateBalance(int id, int count);

    public void delete(int id) throws NotFoundException;

    public User get(int id) throws NotFoundException;

    public User getByEmail(String email) throws NotFoundException;

    public List<User> getAll();

    public void update(User user) throws NotFoundException;

    public void update(UserTo user) throws NotFoundException;

    public void evictCache();

    void enable(int id, boolean enable);
}
