package com.mljr.gps.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @description:
 * @Date : 2018/8/22$ 11:00$
 * @Author : liht
 */
@Data
public class LoginVo {
    /**
     * 加密的密钥
     */
    public static final String DES_ENCRYPT_KEY = "notLogin";
    /**
     * {@link com.mljr.util.DESUtil#encrypt(String, String)}
     */
    @ApiModelProperty(value = "用户鉴权")
    private String userSign;
    @ApiModelProperty(value = "用户的级别")
    private String userLevel;
    @ApiModelProperty(value = "用户所属菜单")
    private List<SyItemVo> syItemVoList;
    @ApiModelProperty(value = "真实姓名")
    private String trueName;
}
