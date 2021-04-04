package com.maserhe.enums;

/**
 * 描述:
 * 用户的类型
 *
 * @author Maserhe
 * @create 2021-04-03 16:27
 */
public enum UserType {

    /**
     * 普通用户, 管理员, 版主
     */

    NORMAL(1),
    ADMIN(2),
    MONITOR(3);

    // '0-普通用户; 1-超级管理员; 2-版主
    private final Integer type;

    public Integer getType() {
        return type;
    }

    UserType(Integer type) {
        this.type = type;
    }
}
