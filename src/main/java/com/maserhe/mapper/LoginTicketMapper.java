package com.maserhe.mapper;

import com.maserhe.entity.LoginTicket;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

/**
 * 描述:
 * 登录凭证的Mapper
 *
 * @author Maserhe
 * @create 2021-04-03 15:03
 */
@Mapper
public interface LoginTicketMapper {

    int insertLoginTicket(LoginTicket ticket);
    LoginTicket selectTicket(String ticket);
    int updateStatus(String ticket, int status);
    int updateDateAndStatus(String ticket, int status, Date expired);
    List<LoginTicket> selectAllTicket();

}
