package com.mljr.gps.service.impl;

import com.mljr.gps.entity.SyArgControl;
import com.mljr.gps.form.SyArgControlForm;
import com.mljr.gps.mapper.SyArgControlMapper;
import com.mljr.gps.service.SyArgControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 数据字典Service
 * @Date : 2018/6/19 下午5:31
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
@Service
public class SyArgControlServiceImpl implements SyArgControlService {

	@Autowired
	private SyArgControlMapper syArgControlMapper;

	@Override
	public List<SyArgControl> queryList(SyArgControlForm form) {
		return syArgControlMapper.queryList(form);
	}

	@Override
	public List<SyArgControl> queryList(Integer conArgType) {
		return queryList(new SyArgControlForm(conArgType));
	}
}
