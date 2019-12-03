package com.gw.dev.hcbq.entity;

import javax.persistence.*;

@Entity
@Table(name = "T_Sys_Group_User")
public class GroupUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String userId;
    private String GroupUser;

    @Override
    public String toString() {
        return "GroupUser{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", GroupUser='" + GroupUser + '\'' +
                '}';
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGroupUser() {
        return GroupUser;
    }

    public void setGroupUser(String groupUser) {
        GroupUser = groupUser;
    }
}
