package com.mljr.gps.mapper;

import com.mljr.gps.entity.SyArgControl;
import com.mljr.gps.form.SyArgControlForm;

import java.util.List;
/**
 * @description: 数据字典Mapper
 * @Date : 2018/6/19 下午5:28
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
public interface SyArgControlMapper{
	/**
	 * 查询集合
	 * @param form
	 * @return
	 */
	List<SyArgControl> queryList(SyArgControlForm form);
}