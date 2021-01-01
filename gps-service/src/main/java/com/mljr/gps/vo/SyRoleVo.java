package com.mljr.gps.vo;

import com.mljr.gps.base.vo.BaseVo;
import com.mljr.gps.entity.SyUser;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @description:
 * @Date : 2019/1/10$ 14:08$
 * @Author : liht
 */
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class SyRoleVo extends BaseVo{
    @ApiModelProperty(value = "角色id")
    private String roleId;
    @ApiModelProperty(value = "角色名称")
    private String roleName;
    @ApiModelProperty(value = "角色类型")
    private String roleType;
    @ApiModelProperty(value = "角色描述")
    private String roleDesc;

    private String updateTime;

    private String createTime;
    @ApiModelProperty(value = "是否有效")
    private String validFalg;

    private List<SyUser> users;
}
