package com.maserhe.service;

import com.maserhe.entity.DiscussPost;
import com.maserhe.mapper.DiscussPostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 描述:
 *
 * @author Maserhe
 * @create 2021-04-02 14:44
 */
@Service
public class DiscussPostService {

    @Autowired
    private DiscussPostMapper discussPostMapper;

    public List<DiscussPost> findDiscussPosts(int userId, int offset, int limit) {
        return discussPostMapper.getAllDiscussPost(userId, offset, limit);
    }

    /**
     * 统计用户发的 帖子数量
     * @param userId
     * @return
     */
    public int findDiscussPostRows(int userId) {
        return discussPostMapper.getDiscussPostRows(userId);
    }

    /**
     * 查询帖子 通过id
     * @param id
     * @return
     */
    public DiscussPost findDiscussPostById(int id) {
        return discussPostMapper.selectDiscussPostById(id);
    }

    /**
     *  增加帖子
     * @param post
     * @return
     */
    public int addDiscussPost(DiscussPost post) {
        return discussPostMapper.insertDiscussPost(post);
    }
}
