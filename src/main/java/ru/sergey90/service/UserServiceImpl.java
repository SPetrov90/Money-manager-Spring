package ru.sergey90.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sergey90.entity.User;
import ru.sergey90.repository.UserRepository;
import ru.sergey90.util.user.LoggedUser;
import ru.sergey90.util.user.UserTo;
import ru.sergey90.util.user.UserUtil;
import ru.sergey90.util.exteption.ExceptionUtil;
import ru.sergey90.util.exteption.NotFoundException;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository repository;

    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public int updateBalance(int id, int count){
        return repository.updateBalance(id, count);
    }

    @CacheEvict(value = "users", allEntries = true)
    public void delete(int id) {
        ExceptionUtil.check(repository.delete(id), id);
    }

    public User get(int id) throws NotFoundException {
        return ExceptionUtil.check(repository.get(id), id);
    }

    public User getByEmail(String email) throws NotFoundException {
        return ExceptionUtil.check(repository.getByEmail(email), "email=" + email);
    }

    public List<User> getAll() {
        return repository.getAll();
    }

    @Override
    @Transactional
    public void update(User user) throws NotFoundException {
        User oldUser = get(user.getId());
        UserUtil.update(oldUser, user);
        ExceptionUtil.check(repository.save(oldUser), user.getId());
    }

    @Override
    @Transactional
    public void update(UserTo user) throws NotFoundException {
        User oldUser = get(user.getId());
        UserUtil.updateFromTo(oldUser, user);
        ExceptionUtil.check(repository.save(oldUser), user.getId());
    }

    @Override
    public void evictCache() {
    }

    @Override
    public void enable(int id, boolean enable) {
        repository.enable(id, enable);
    }

    @Override
    public LoggedUser loadUserByUsername(String email) throws UsernameNotFoundException {
        User u = repository.getByEmail(email);
        if (u == null) {
            throw new UsernameNotFoundException("User " + email + " is not found");
        }
        return new LoggedUser(u);
    }
}