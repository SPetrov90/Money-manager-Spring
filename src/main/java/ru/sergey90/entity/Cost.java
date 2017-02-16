package ru.sergey90.entity;

import javax.persistence.*;

@Entity
@DiscriminatorValue("cost")
public class Cost extends Operation {
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private CostType costType;

    public CostType getCostType() {
        return costType;
    }

    public void setCostType(CostType costType) {
        this.costType = costType;
    }

    @Override
    public String toString() {
        return "Cost{" +
                "dateTime=" + this.getId() +
                "dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", moneyCount=" + moneyCount +
                "costType=" + costType +
                '}';
    }
}
