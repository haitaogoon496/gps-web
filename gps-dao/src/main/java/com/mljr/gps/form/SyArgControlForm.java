package com.mljr.gps.form;

import com.lyqc.base.common.BaseForm;
import lombok.Data;

import java.util.List;

/**
 * @description: 数据字典Form
 * @Date : 2018/6/19 下午5:33
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
@Data
public class SyArgControlForm extends BaseForm {
    private static final long serialVersionUID = 5712562973876418082L;
    
    private Integer conArgType;

    private Integer conArgStatus;

	private String conArgValue;

	private String conArgName;

	private List<String> argValList;

	private String conArgCode;

	public SyArgControlForm() {
	}

	public SyArgControlForm(Integer conArgType) {
		this.conArgType = conArgType;
	}
}
