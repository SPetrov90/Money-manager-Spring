package ru.sergey90.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ru.sergey90.config.DataConfig;
import ru.sergey90.config.WebConfig;
import ru.sergey90.entity.Role;
import ru.sergey90.entity.User;
import ru.sergey90.service.UserService;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {DataConfig.class, WebConfig.class})
public class UserTest {
    @Autowired
    private UserService userService;

    public void addUder(){
        User user = new User("vasya", "aaaa@mail.ru", "erjrifrf", true, Role.ROLE_USER);
        userService.save(user);
    }
    @Test
    public void getUserBalance(){
        User user = userService.get(100000);
        userService.updateBalance(user.getId(), 177);
    }

}
