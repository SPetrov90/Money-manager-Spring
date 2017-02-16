package ru.sergey90.entity;

import javax.persistence.*;

@Entity
@DiscriminatorValue("income")
public class Income extends Operation {
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private IncomeType incomeType;

    public IncomeType getIncomeType() {
        return incomeType;
    }

    public void setIncomeType(IncomeType incomeType) {
        this.incomeType = incomeType;
    }

    @Override
    public String toString() {
        return "Income{" +
                "dateTime=" + this.getId() +
                "dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", moneyCount=" + moneyCount +
                "incomeType=" + incomeType +
                '}';
    }

}
