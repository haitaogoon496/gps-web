package com.mljr.gps.form;

import com.lyqc.base.common.BaseForm;
import lombok.Data;

/**
 * @description: 系统用户
 * @Date : 上午10:08 2018/3/4
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
@Data
public class SyUserForm extends BaseForm {
    private static final long serialVersionUID = 1227570672679917728L;

    private Integer userId;

    private String userName;

    private String trueName;

    private String userStatus;

    private String userType;
}
