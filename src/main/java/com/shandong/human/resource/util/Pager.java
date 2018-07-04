package com.shandong.human.resource.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义分页类
 * 分页信息主要包括几个：
 * 当前页|每页显示数|总页数|总数量|分页返回的信息
 *
 * @author tyee.noprom@qq.com
 * @time 2/12/16 11:00 AM.
 * <p>
 * SYC 更改于 3/19/16
 */
public class Pager<T> {

    /**
     * 当前页数
     */
    private int currentPage;

    /**
     * 每页显示数
     */
    private int size;

    /**
     * 每页最大显示数
     */
    private int maxSize;

    /**
     * 总页数
     */
    private int totalPage;

    /**
     * 记录总数
     */
    private int count;

    /**
     * 分页信息
     */
    private List<T> data;

    /**
     * @param maxSize 单页最多显示数目
     * @brief 构造函数
     */
    public Pager(int maxSize) {
        this.maxSize = maxSize;
        if (maxSize < 1)
            this.maxSize = 1;

        data = new ArrayList<T>();
        currentPage = 1;
    }

    public void setCount(int count) {
        this.count = count;

        totalPage = count / maxSize;
        if (totalPage * maxSize < count)
            totalPage++;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setData(List<T> data) {
        this.data = data;
        size = data.size();
    }

    public int getCount() {
        return count;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public int getSize() {
        return size;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public List<T> getData() {
        return data;
    }
}
