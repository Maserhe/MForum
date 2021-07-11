package com.maserhe.service;

import com.maserhe.entity.Comment;
import com.maserhe.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 描述:
 * 评论的业务
 *
 * @author Maserhe
 * @create 2021-04-05 18:36
 */

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;


    /**
     * 根据帖子 查询所有的评论
     * @param entityId
     * @param offset
     * @param limit
     * @return
     */
    public List<Comment> findComments(int entityId, int offset, int limit) {
        return commentMapper.selectCommentsById(entityId, offset, limit);
    }

    /**
     * 根据 帖子id 查询所有帖子。
     * @param entityId
     * @return
     */
    public int findCommentsCount(int entityId) {
        return commentMapper.selectCountByEntity(entityId);
    }

    /**
     * 判断用户是否为 某个帖子打过分
     * @param userId
     * @param entityId
     * @return
     */
    public boolean isDoStar(int userId, int entityId) {
        return commentMapper.selectCommentStatus(userId, entityId) >= 1;
    }

    /**
     * 添加评论
     * @param comment
     * @return
     */
    public int addComment(Comment comment) {
        return commentMapper.addComment(comment);
    }

}
