package com.maserhe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 描述:
 * 封装分页的信息
 *
 * @author Maserhe
 * @create 2021-04-02 19:03
 */
public class Page {

    /**
     * 当前页面
     */
    private int current = 1;

    /**
     * 每页的限制
      */
    private int limit = 10;

    /**
     * 数据总数
     */
    private int rows;

    /**
     * 查询的路径（查询分页的链接）
     */


    private String path;

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        if( current >= 1) this.current = current;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        if (limit >= 1 && limit <= 100 )
        this.limit = limit;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 获取当前页的 起始行。
     * @return
     */
    public int getOffset() {
        return (current - 1) * limit;
    }

    /**
     * 获取总页数
     */
    public int getTotal() {
        int ans = rows / limit;
        if (rows % limit != 0) ans += 1;
        return ans;
    }

    /**
     * 获取起始页码
     */
    public int getTo() {
        int to = current + 2;
        return to > getTotal()? getTotal(): to;
    }

    /**
     * 获取 末尾页
     */
    public int getFrom() {
        int from = current - 2;
        return from < 1? 1: from;
    }

}
