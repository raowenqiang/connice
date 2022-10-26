package com.connice.common.util;

import lombok.Data;

import java.util.List;

/**
 * @Author: WenQiangRao
 * @Description:
 * @Date: Created in 16:46 2022/10/8
 * Modified By:
 **/
@Data
public class PageResult<T> {

    private Long total;
    private List<T> rows;

    public PageResult(Long total,List<T> rows){
        super();
        this.total = total;
        this.rows = rows;
    }
}
