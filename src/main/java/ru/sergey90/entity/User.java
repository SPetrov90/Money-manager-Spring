package ru.sergey90.entity;

import ru.sergey90.util.user.AbstractUser;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "email", name = "unique_email")})
public class User extends NamedEntity implements AbstractUser {

    @Column(name = "email", nullable = false, unique = true)
    protected String email;

    @Column(name = "password", nullable = false)
    protected String password;

    @Column(name = "enabled", nullable = false)
    protected boolean enabled = true;

    @Column(name = "registered", columnDefinition = "timestamp default now()")
    @NotNull
    protected Date registered = new Date();

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)

    protected Set<Role> roles;

    @Column(name = "balance", nullable = false, columnDefinition = "default 0")
    protected int balance = 0;

    public User() {
    }

    public User(User u) {
        this(u.getId(), u.getName(), u.getEmail(), u.getPassword(), u.isEnabled(), u.getRoles());
    }


    public User(Integer id, String name, String email, String password, boolean enabled, Role role, Role... roles) {
        this(id, name, email, password, enabled, EnumSet.of(role, roles));
    }

    public User(Integer id, String name, String email, String password, boolean enabled, Set<Role> roles) {
        super(id, name);
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.roles = roles;
    }

    public User( String name, String email, String password, boolean enabled, Role role) {
        super(name);
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.roles = new HashSet<>();
        this.roles.add(role);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int getBalance() {
        return balance;
    }

    @Override
    public void setBalance(int balance) {
        this.balance = balance;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public String getPassword() {
        return password;
    }

    public void setRoles(Role... authorities) {
        setRoles(Arrays.asList(authorities));
    }

    public void setRoles(Collection<Role> authorities) {
        this.roles = EnumSet.copyOf(authorities);
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", registered=" + registered +
                ", roles=" + roles +
                ", balance=" + balance +
                '}';
    }
}
