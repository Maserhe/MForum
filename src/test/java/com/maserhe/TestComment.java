package com.maserhe;

import com.maserhe.entity.Comment;
import com.maserhe.service.CommentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

/**
 * 描述:
 *  测试评论功能
 * @author Maserhe
 * @create 2021-04-05 18:40
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestComment {

    @Autowired
    private CommentService commentService;

    @Test
    public void test() {
        List<Comment> comments = commentService.findComments(228, 0, 100);
        for (int i = 0; i < comments.size(); i++) {
            System.out.println(comments.get(i));
        }
    }

    @Test
    public void testStatus() {
        System.out.println(commentService.isDoStar(168, 275));

        /*
        Comment comment = new Comment();
        comment.setEntityId(274);
        comment.setUserId(168);
        comment.setContent("哈哈哈哈哈可以的");
        comment.setCreateTime(new Date());
        commentService.addComment(comment);
         */
    }



}
