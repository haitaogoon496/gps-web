package com.mljr.gps.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.sf.oval.constraint.NotNull;

import java.io.Serializable;

/**
 * @description:
 * @Date : 2018/12/5$ 16:28$
 * @Author : liht
 */
@Data
public class UpdateUserParam implements Serializable {

    @ApiModelProperty(value = "原密码")
    @NotNull(message = "原密码不能为空")
    private String password;
    @ApiModelProperty(value = "新密码")
    @NotNull(message = "新密码不能为空")
    private String newPassword;
    @ApiModelProperty(value = "确认密码")
    @NotNull(message = "确认密码不能为空")
    private String confirmPassword;


}
