package com.maserhe.service;

import com.maserhe.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * 描述:
 * Redis的业务处理
 *
 * @author Maserhe
 * @create 2021-04-10 10:36
 */
@Service
public class RedisService {

    @Autowired
    private RedisTemplate template;

    /**
     * 为 这个Id 点赞  set 集合里面存取所有点赞的用户
     * @param discussPostId
     * @param userId 点赞用户的ID
     */
    public boolean like(int discussPostId, int userId){
        String key = RedisUtil.getKey(discussPostId);
        BoundSetOperations operations = template.boundSetOps(key);
        Boolean isMember = operations.isMember(userId);
        if (isMember) {
            // 取消点赞
            operations.remove(userId);
            return false;
        } else {
            operations.add(userId);
            return true;
        }
    }

    /**
     * 统计某一个帖子的全部点赞数量
     * @param discussPostId
     * @return
     */
    public long count(int discussPostId) {
        String key = RedisUtil.getKey(discussPostId);
        BoundSetOperations operations = template.boundSetOps(key);
        return operations.size();
    }

}
