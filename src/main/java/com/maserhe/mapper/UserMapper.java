package com.maserhe.mapper;

import com.maserhe.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 描述:
 *
 * @author Maserhe
 * @create 2021-04-02 9:15
 */
@Mapper
public interface UserMapper {

    User selectById(int id);

    User selectByName(String username);

    User selectByEmail(String email);

    int insertUser(User user);

    int updateStatus(int id, int status);

    int updateHeader(int id, String headerUrl);

    int updatePassword(int id, String password);
}
