package com.maserhe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 描述:
 *
 * @author Maserhe
 * @create 2021-04-02 9:09
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    private int id;
    private String username;
    private String password;
    private String salt;

    private String email;
    private int type;
    private int status;

    private String activationCode;
    private String headerUrl;
    private Date createTime;

}
