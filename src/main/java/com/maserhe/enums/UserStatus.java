package com.maserhe.enums;

/**
 * 描述:
 * 用户账号的状态
 * @author Maserhe
 * @create 2021-04-03 16:19
 */
public enum UserStatus {


    NO_ACTIVATION(0),
    ACTIVATION(1);
    private final Integer status;

    UserStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

}
