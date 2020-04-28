package com.wzh.util;

/**
 * @Author: wzh
 * @ClassName: PageUtil
 * @Description: 封装分页相关信息
 * @Date: 2020/4/28 11:14
 */
public class PageUtil {
    //当前页码
    private int currentPage = 1;
    //每页显示条数
    private int pageSize = 10;
    //总条数
    private int rows;
    //查询路径(用于分页的url路径)
    private String path;

    public int getCurrent() {
        return currentPage;
    }

    public void setCurrent(int current) {
        if (current > 1) {
            this.currentPage = current;
        }
    }

    public int getLimit() {
        return pageSize;
    }

    public void setLimit(int limit) {
        if (limit >= 1 && limit <= 100)
            this.pageSize = limit;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        if (rows >= 0) {
            this.rows = rows;
        }
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 获取当前页的起始行
     *
     * @return
     */
    public int getOffset() {
        return (currentPage - 1) * pageSize;
    }

    /**
     * 获取总页数
     *
     * @return
     */
    public int getTotalPage() {
        if (rows % pageSize == 0) {
            return rows / pageSize;
        } else {
            return rows / pageSize + 1;
        }
    }

    /**
     * 获取起始页码
     *
     * @return
     */
    public int getForm() {
        int form = currentPage - 2;
        return form < 1 ? 1 : form;
    }

    /**
     * 获取结束页码
     *
     * @return
     */
    public int getTo() {
        int to = currentPage + 2;
        int total = getTotalPage();
        return to > total ? total : to;
    }
}
