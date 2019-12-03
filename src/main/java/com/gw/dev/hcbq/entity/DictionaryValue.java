package com.gw.dev.hcbq.entity;

import javax.persistence.*;

@Entity
@Table(name = "T_Sys_Dictionary_Value")
public class DictionaryValue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String dicCode;
    private String value;
    private int sort;


    @Override
    public String toString() {
        return "DictionaryValue{" +
                "id='" + id + '\'' +
                ", dicCode='" + dicCode + '\'' +
                ", value='" + value + '\'' +
                ", sort=" + sort +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDicCode() {
        return dicCode;
    }

    public void setDicCode(String dicCode) {
        this.dicCode = dicCode;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}
