package cn.mir.common.utilities;

import lombok.Data;

import java.util.List;

/**
 * 存放分页结果集
 * <p>
 * Create time: 2019-05-09 16:33
 * </p>
 *
 * @author 周光兵
 */
@Data
public class PageResult<T> {
    /**
     * 记录总数
     */
    private int total;
    /**
     * 分页总数
     */
    private int pages;
    /**
     * 返回的数据集
     */
    private List<T> list;

    public PageResult(int total, List<T> list) {
        this.total = total;
        this.list = list;
    }

    public PageResult(int total, int pages, List<T> list) {
        this.total = total;
        this.pages = pages;
        this.list = list;
    }
}