package com.maserhe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 描述:
 *
 * @author Maserhe
 * @create 2021-04-02 13:13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiscussPost {

    private int id;
    private String userId;
    private String title;

    private String content;
    private int type;
    private int status;

    private Date createTime;
    private int commentCount;
    private double score;

    private String imageUrl;


}
