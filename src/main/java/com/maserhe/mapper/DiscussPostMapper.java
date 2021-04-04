package com.maserhe.mapper;

import com.maserhe.entity.DiscussPost;
import com.maserhe.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * 描述:
 *
 * @author Maserhe
 * @create 2021-04-02 13:17
 */
@Mapper
public interface DiscussPostMapper {

    /**
     * 获取 所有的 帖子， 如果用户userId 为零，代表 查询所有。
     * @param userId
     * @return
     */
    List<DiscussPost> getAllDiscussPost(int userId, int offset, int limit);

    /**
     * 获取 帖子的 数量， 如果用户userId 为零，代表 查询所有。
     * @param userId
     * @return
     */
    int getDiscussPostRows(@Param("userId") int userId);


}
