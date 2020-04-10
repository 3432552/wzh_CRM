package com.wzh.common;

import lombok.Data;
import lombok.ToString;

import java.util.List;
import java.util.Map;

/**
 * @Author: wzh
 * @ClassName: PaginationVo
 * @Description:
 * @Date: 2020/4/5 21:01
 */
@Data
@ToString
public class PaginationVo<T> {
    private int total;
    private List<T> actList;
}
