package com.gw.dev.hcbq.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "T_Sys_Dictionary")
public class Dictionary implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String dicCode;
    private String dicName;
    @Override
    public String toString() {
        return "Dictionary{" +
                "id='" + id + '\'' +
                ", dicCode='" + dicCode + '\'' +
                ", dicName='" + dicName + '\'' +
                '}';
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDicCode(String dicCode) {
        this.dicCode = dicCode;
    }

    public void setDicName(String dicName) {
        this.dicName = dicName;
    }

    public String getId() {
        return id;
    }

    public String getDicCode() {
        return dicCode;
    }

    public String getDicName() {
        return dicName;
    }
}
