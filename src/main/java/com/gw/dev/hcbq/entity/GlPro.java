package com.gw.dev.hcbq.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_glpro")
public class GlPro {
    @Id
    private String id;
    private String proId_1;
    private String proId_2;

    @Override
    public String toString() {
        return "GlPro{" +
                "id='" + id + '\'' +
                ", proId_1='" + proId_1 + '\'' +
                ", proId_2='" + proId_2 + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProId_1() {
        return proId_1;
    }

    public void setProId_1(String proId_1) {
        this.proId_1 = proId_1;
    }

    public String getProId_2() {
        return proId_2;
    }

    public void setProId_2(String proId_2) {
        this.proId_2 = proId_2;
    }
}
