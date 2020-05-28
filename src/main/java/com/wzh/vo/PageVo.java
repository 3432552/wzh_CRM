package com.wzh.vo;

import com.wzh.domain.Activity;
import lombok.Data;

import java.util.List;

@Data
public class PageVo {
    //当前页码
    private int currentPage;
    //页码数
    private int totalPages;
    //总共条数
    private int totalRows;
    private List<Activity> dataList;
}
