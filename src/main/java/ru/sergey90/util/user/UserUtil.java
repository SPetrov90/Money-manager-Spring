package ru.sergey90.util.user;


import ru.sergey90.entity.Role;
import ru.sergey90.entity.User;

public class UserUtil {

    public static User createFromTo(UserTo newUser) {
        return new User(null, newUser.getName(), newUser.getEmail().toLowerCase(),
                PasswordUtil.encode(newUser.getPassword()), true, Role.ROLE_USER);
    }

    public static UserTo asTo(AbstractUser user) {
        return new UserTo(user.getId(), user.getName(), user.getEmail(), user.getBalance());
    }

    public static User updateFromTo(User oldUser, UserTo updatedUser) {
        return updateInternal(oldUser, updatedUser);
    }

    public static User update(User oldUser, User updatedUser) {
        oldUser.setRoles(updatedUser.getRoles());
        oldUser.setEnabled(updatedUser.isEnabled());
        return updateInternal(oldUser, updatedUser);
    }

    public static <T extends AbstractUser> T encode(T u) {
        u.setPassword(PasswordUtil.encode(u.getPassword()));
        return u;
    }

    private static User updateInternal(User oldUser, AbstractUser updatedUser) {
        String password = updatedUser.getPassword();
        if (password != null) {
            oldUser.setPassword(PasswordUtil.encode(password));
        }
        oldUser.setName(updatedUser.getName());
        oldUser.setEmail(updatedUser.getEmail().toLowerCase());
        oldUser.setBalance(updatedUser.getBalance());
        return oldUser;
    }
}
