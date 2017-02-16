package ru.sergey90.repository;

import ru.sergey90.entity.User;

import java.util.List;

public interface UserRepository {
    User save(User user);

    // false if not found
    boolean delete(int id);

    // null if not found
    User get(int id);

    // null if not found
    User getByEmail(String email);

    List<User> getAll();

    int getBalance(int id);

    int updateBalance(int id, int count);

    default void enable(int id, boolean enable) {
        throw new UnsupportedOperationException("Enable for this profile is not supported");
    }
}
