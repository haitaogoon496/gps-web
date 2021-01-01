package com.mljr.gps.base.service;

import com.lyqc.base.common.BaseForm;
import com.lyqc.base.page.PageForm;
import com.mljr.gps.base.entity.BaseEntity;

import java.util.List;

/**
 * @description: Service接口
 * @Date : 2018/6/3 下午6:05
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
public interface BaseService<E extends BaseEntity,FK,F extends BaseForm> {
    /**
     * 分页加载数据
     * @param form
     * @return
     */
    List<E> queryByPage(PageForm<F> form);
    /**
     * 分页查询数量
     * @param form
     * @return
     */
    int queryCount(PageForm<F> form);
    /**
     * 查询列表
     * @param form
     * @return
     */
    default List<E> queryList(F form){return null;}
    /**
     * 查询实体对象
     * @param primaryKey
     * @return
     */
    E queryRecord(FK primaryKey);
    /**
     * 新增实体对象
     * @param record
     * @return
     */
    int insertRecord(E record);
    /**
     * 修改实体对象
     * @param record
     * @return
     */
    int updateRecord(E record);
    /**
     * 删除对象
     * @param primaryKey
     * @return
     */
    int deleteRecord(FK primaryKey);
    /**
     * 批量新增
     * @param list
     * @return
     */
    default int batchInsert(List<E> list){
        return 0;
    }
    /**
     * 批量插入(没有即插入，有的话不做处理)
     * @param ignoreList
     * @return
     */
    default int batchInsertIgnore(List<E> ignoreList){
        return 0;
    }
}
