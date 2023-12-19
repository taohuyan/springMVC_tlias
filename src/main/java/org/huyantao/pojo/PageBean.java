package org.huyantao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author:deconglin
 * @date:2023-08-06-20:18
 * @description:
 */

/*
分页查询结果的封装类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageBean {
    private Long total;//总记录数
    private List rows; //数据列表
}
