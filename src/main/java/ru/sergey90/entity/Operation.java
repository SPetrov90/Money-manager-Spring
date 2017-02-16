package ru.sergey90.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "operation")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(      name="operation_type",
        discriminatorType=DiscriminatorType.STRING      )
@DiscriminatorValue("operation")
public class Operation extends BaseEntity {

    @Column(name = "datetime", nullable = false)
    protected LocalDateTime dateTime;

    @Column(name = "description", nullable = false)
    @NotEmpty
    protected String description;

    @Column(name = "money_count")
    protected int moneyCount;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public int getMoneyCount() {
        return moneyCount;
    }

    public void setMoneyCount(int moneyCount) {
        this.moneyCount = moneyCount;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "dateTime=" + this.getId() +
                "dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", moneyCount=" + moneyCount +
                '}';
    }
}
