package com.macro.mall.common.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 分页数据的封装类
 * @Author Zhangnana
 * @DATE 2020/12/12 10:10
 * @Version 1.0
 */
public class CommonPage<T> {

    private Integer pageNum;
    private Integer pageSize;
    private Integer  totalPage;
    private Long total;
    private List<T> list;



    /**
     * 将pageHelper分页后的list转化为分页信息
     * @param list
     * @param <T> 泛型
     * @return
     */
    public static <T> CommonPage<T> resetPage(List<T> list){
        CommonPage<T> result = new CommonPage<>();
        PageInfo<T> pageInfo = new PageInfo<>(list);
        result.setPageNum(pageInfo.getPageNum());
        result.setPageSize(pageInfo.getPageSize());
        result.setTotal(pageInfo.getTotal());
        result.setList(pageInfo.getList());
        result.setTotalPage(pageInfo.getPages());
        return result;
    }

    public static <T> CommonPage<T> resetPage(Page<T> page){
        CommonPage<T> result = new CommonPage<>();
        result.setPageNum(page.getNumber());
        result.setTotalPage(page.getTotalPages());
        result.setPageSize(page.getSize());
        result.setTotal(page.getTotalElements());
        result.setList(page.getContent());
        return result;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }


    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }
}
