package com.enviro.assessment.grad001.MontelWood.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "Recycling-Tips")
public class RecyclingTipEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    public long getId() {
        return Id;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public void setId(long id) {
        Id = id;
    }

    private String tip;
}
