package com.maserhe.mapper;

import com.maserhe.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 描述:
 * 评论的Mapper
 *
 * @author Maserhe
 * @create 2021-04-05 18:21
 */
@Mapper
public interface CommentMapper {

    /**
     * 分页查询 查询某一个帖子的数量
     * @param entityId
     * @param offset
     * @param limit
     * @return
     */
    List<Comment> selectCommentsById(int entityId, int offset, int limit);

    /**
     * 计算评论的总数
     * @param entityId
     * @return
     */
    int selectCountByEntity(int entityId);

    /**
     * 用户查询 是否有打过分。
     * @param userId
     * @param entityId
     * @return
     */
    int selectCommentStatus(int userId, int entityId);

    /**
     * 添加评论
     * @param comment
     * @return
     */
    int addComment(Comment comment);

}
