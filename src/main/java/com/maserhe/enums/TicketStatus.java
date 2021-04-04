package com.maserhe.enums;

/**
 * 描述:
 * 登陆凭证的状态2
 *
 * @author Maserhe
 * @create 2021-04-03 17:01
 */
public enum TicketStatus {

    INVALID(1),
    EFFECTIVE(0);

    private final int status;

    TicketStatus(Integer status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
