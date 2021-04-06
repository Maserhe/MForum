package com.maserhe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 描述:
 * 评论打分
 *
 * @author Maserhe
 * @create 2021-04-05 18:13
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    private int id;
    private int userId;      // 谁的评论
    private int entityId;    // 具体是哪个帖子
    private int targetId;    // 给指向某个人的评论
    private String content;  // 评论的内容
    private int status;      // 状态 0， 1；
    private Date createTime; //评论的时间
    private int star;        // 打分

}
