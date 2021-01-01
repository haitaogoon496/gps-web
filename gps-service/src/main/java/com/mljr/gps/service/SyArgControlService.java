package com.mljr.gps.service;

import com.mljr.gps.entity.SyArgControl;
import com.mljr.gps.form.SyArgControlForm;

import java.util.List;

/**
 * @description: 数据字典Service
 * @Date : 2018/6/19 下午5:27
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
public interface SyArgControlService {
	/**
	 * 查询集合
	 * @param form
	 * @return
	 */
	List<SyArgControl> queryList(SyArgControlForm form);
	/**
	 * 根据业务类型查询集合
	 * @param conArgType
	 * @return
	 */
	List<SyArgControl> queryList(Integer conArgType);
}
