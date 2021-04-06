package com.maserhe.enums;

/**
 * 描述:
 *
 * @author Maserhe
 * @create 2021-04-03 17:33
 */
public interface LoginParam {

    /**
     * 激活成功
     */
    int ACTIVATION_SUCCESS = 0;

    /**
     * 重复激活
     */
    int ACTIVATION_REPEAT = 1;

    /**
     * 激活失败
     */
    int ACTIVATION_FAILURE = 2;

    /**
     * 默认状态下 的 凭证超时时间
     */
    long DEFAULT_EXPIRED_SECONDS = 3600 * 12;

    /**
     * 记住我状态下 的 超时时间
     */
    long REMEMBER_EXPIRED_SECONDS = 3600 * 24 * 30;

    /**
     * 帖子
     */
    int ENTITY_TYPE_POST = 1;

    /**
     * 评论
     */
    int ENTITY_TYPE_COMMENT = 2;

}
